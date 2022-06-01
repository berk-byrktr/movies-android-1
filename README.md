Android app connects to the Movie DB API.

## Descreption

This is a simple android app connects to the Movies DB API, fetch popular movies and present some
basic information related to each one of them.

## Library Stack

- **App Compat - Material - AndroidX Core** support libraries
- **Glide** for image loading and catching
- **BaseRecyclerViewAdapterHelper** recycler view adapter
- **Dagger** to provide dependency injection
- **Retrofit - OkHttp3** for request/response API
- **RxJava - RXAndroid** reactive programming paradigm
- **LiveData** use LiveData to see UI update with data changes
- **Data Binding** bind UI components in layouts to data sources

## App Architecture

- Follow the rules from Clean and MVVM Architectures guidelines
- Keep Fragment/Activity responsible only for the code related to UI
- Splitted into four main layers (common - data - domain - ui)

## About Development

- All UI components were implemented as custom view
- Simple request's error/retry handling was implemented