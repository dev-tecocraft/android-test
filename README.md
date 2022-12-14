# Android-Test
## Login Activity with data validation, Login API call using retrofit and store User data using Room database.

### Features Used in Test project

- MVVM (Model-View-ViewModel) Clean Architecture
- Koin for dependency injection
- Room persistence for local data base
- Retrofit for Network API call
- StateFlow for reactive programing
- Test case

## Test App work flow

- On start User can see login screen, after entering valid email and password user can able to login. Once user logged In successfully, The user data which is get from the API and X-ACC token(which is JWT token) store in local database, And User will redirect to the Another screen in which user can See it's User name and User id.

## Functionality Description

- [LoginActivityViewModel](https://github.com/dev-tecocraft/android-test/blob/main/app/src/main/java/com/imaginato/homeworkmvvm/ui/login/LoginActivityViewModel.kt) contains business login and responsible for UI changes in [LoginActivity](https://github.com/dev-tecocraft/android-test/blob/main/app/src/main/java/com/imaginato/homeworkmvvm/ui/login/LoginActivity.kt) Activity, In which includes functionality like _API call, User data store in Local database And Data validation_. And It's hold the particular state of UI as per User interactions using _State Flow_.
- [IModules.kt](https://github.com/dev-tecocraft/android-test/blob/main/app/src/main/java/com/imaginato/homeworkmvvm/domain/IModules.kt) is Responsible for providing singleton objects which is used by application like Database object and Retrofit object.
- [LoginActivityViewModelTest](https://github.com/dev-tecocraft/android-test/blob/main/app/src/androidTest/java/com/imaginato/homeworkmvvm/ui/login/LoginActivityViewModelTest.kt) is the test class of [LoginActivityViewModel](https://github.com/dev-tecocraft/android-test/blob/main/app/src/main/java/com/imaginato/homeworkmvvm/ui/login/LoginActivityViewModel.kt) which contains test case functions for Valid and InValid Email address.