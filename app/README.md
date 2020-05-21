# Paginated Cacheable Repository
## Tutorial (Specific to Drag)

**Suppose you want to fetch `User` objects from a paginated api that return the reponse in the following JSON structure:**

```
{
    pages: 1
    records: [{user1details}, {user2details}.........]
}
```

**Follow the below steps to create a repository for such response that is Cacheable**

### Step 1. Create a Room Entity
If you already have a model class, just annotate with Room annotation to convert it into a Room entity

`User.java`
```
@Entity (tableName = "table_users")
public class User {
    @PrimaryKey()
    @NonNull
    @ColumnInfo
    private String id;
    
    @ColumnInfo
    private String name;
    
    public String getId() { return id; }
    public String getName() { return name; }
}
```

### Step 2. Create a DAO
Create a data access object

```
@Dao
public interface UserDao {

    @Query("SELECT * FROM table_users")
    DataSource.Factory<Integer, User> getUsers(); //This will return a DataSource
       
    @Query("DELETE FROM table_users")
    void deleteAll();
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAll(List<User> user);   
}
```

### Note: You must have these two methods in your DAO. 
- One that return a DataSource
- Other that deletes all the data inside a table
- 3rd one that adds a list to the db

### Step 3. Specify your DAO in your Room DB class

```
@Database(entities = {User.class}, version = 1)
public abstract class DragDatabase extends RoomDatabase {
    .
    .
    .
    .
    public abstract UserDao userDao();
}
```

**That's all the Room related stuff. Now lets change our endpoint interface a bit

### Step 4. Change Endpoint interface
Your endpoint interface must return a `Call` object with the type `DragResponseWrapper<PaginatedResponse<YourType>>`

```
@GET("your/blah/endpoint")
Call<DragResponseWrapper<PaginatedResponse<User>>> getUsers(
    @Path("anything") String anythingYouWantAsParams,
    @Query("pageNo") int pageNo, //this is important
    @Query("size") int size //this is important
);
```

**You must use `DragResponseWrapper` and `PaginatedResponse` from the library package not the `app` package**

### Okay, that's the Room and Retrofit related setup. Lets now setup the repository


### Step 5. Create your repository by extending `BaseCacheableRepository<T>`

- The repository class with extend `BaseCacheableRepository<T>`
- It will implement `Cacheable<T>` interface

**Note:** It is recommended to have your repository class as Singleton. The following example however **does not** creates a singleton  for simplicity.

```
public class UsersRepository extends BaseCacheableRepository<User> implements Cacheable<User> {
    private UsersDao dao;
    
    public UsersRepository(Context context) {
        super(context);
        dao = DragDatabase.getInstance(context).userDao();
        setCacheableRepository(this);
    }

    @Override
    public Call<DragResponseWrapper<PaginatedResponse<User>>> getNetworkCallObject(int pageToLoad, int pageSize) {
        return DragAPI.getService().getUsers(yourparameters, pageToLoad, pageSize);
    }

    @Override
    public void persistDataLocally(List<User> list) {
        //save the fetched items to your local db
        dao.addAll(list)
    }

    @Override
    public void clearLocalDatabase() {
        //clear your database
        dao.deleteAll();
    }

    @Override
    protected DataSource.Factory<Integer, User> getDataSource() {
        return dao.getUsers();
    }

    @Override
    protected int getPageSize() {
        //page size for PagedList, use any feasible size
        return 8;
    }

    @Override
    protected int getPrefetchDistance() {
        //prefetch distance for PagedList, use any feasible distance
        return 3;
    }
}
```

### Step 6. Use the repository

Now you may get an instance of the paged list by creating an instance of the created repository

```
UsersRepository repository = new UsersRepository(context);
repository.getPagedList();
repository.getNetworkStateLiveData(); //you can use this to update your UI in case of data LOADING, SUCCESS, FAILURE events
```

If you created a singleton for your repo, then use this:
```
UsersRepository repository = UsersRepository.getInstance(context);
repository.getPagedList(); //this will return a paged list 
repository.getNetworkStateLiveData(); //you can use this to update your UI in case of data LOADING, SUCCESS, FAILURE events
```

- **getPagedList()** will return a paged list that you pass to your normally created PagedListAdapter
- **getNetworkStateLiveData()** will return a `SingleEventLiveData` that you can observe for network states `LOADING` `SUCCESS` `FAILURE`


### Invalidating the data (Reloading the latest data)
If you want to clear the currently stored data from the local database and fetch the latest data from the API, call the following method on your repository:

`repository.invalidate()`

This will clear the local data and will again fetch the latest data from the servers starting page 1 onwards.




