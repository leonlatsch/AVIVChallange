# AVIV Coding Challenge by Leon Latsch

## What I used
* Of course 100% kotlin
* Basic Android Libraries
* Retrofit for API call
* Moshi for Json parsing
* Dagger Hilt for dependency injection
* Compose for UI + navigation
* Coil for image loading
* Timber for logging
* JUnit, mockk and turbine for testing

I chose *not* to write UI tests, because I have little experience with it and we don't do it at my company.

## Features
I implemented both screens with simple UI inspired by https://immoweb.de's mobile web layout.
There is a Loading and a error state for both. As well as loading and error with content (snackbar, pull to refresh).

## Architecture
I used [MVVM](https://de.wikipedia.org/wiki/Model_View_ViewModel) + [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).

There are 3 layers:
- data
- domain
- view

Data is for requesting data from the API.

Domain has domain logic in it. (Not much for this project)

View contains the UI and its models.

If you look at the [Clean Architecture Documentation](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html), *data* and *view* are the *outer layers* and *domain* is the *inner layer*.
Meaning domain models and classes can be used by other layers, but not the other way around.

I also implemented *use cases*. They are used by the view models instead if the repository itself. This way you don't expose functions to the view model that it doesn't need.

## Navigation
I tried compose navigation for the fist time, so the implementation might be a little off.
Also I tried to use a pattern I know that works with navigation events and a navigator (usually in the fragment).
This way navigation from the view model can be tested with unit tests.

## Testing
For testing I wrote unit tests for all classes with logic in it. I did *not* write UI tests as mentioned above.
I also write integration tests for the view models.

When it comes to mocks, I tried to use as much fakes instead of mocks since its faster and independent of frameworks.
You could do this event more by creating interfaces for all your classes, but I think my way shows the idea pretty well.

## Possible expansion stages
You could store the listings in a local database and use it as a single source of truth instead of a in memory flow.
I actually started this but it got a little too complex for a coding challenge.
Although I think this should be the standard way to implement productions apps.

You could also add a search bar to filter the listings.