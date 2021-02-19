# ex1

file structure:
```
/
|-di
| |-- Provider
|-network
| |-- Api
|-utils
| |-- FileUtil
| |-- PrefUtil
|-view
| |-- MainActivity
|-viewmodels
  |-- MainViewModel
```

the view has Main activity with an image and use the view model to get the image uri with live data.

the view model:

fetch the image by:
```
- get the nextImage url
- fetch the file using retrofit.
- save the file to the files dir
- delete the picture dir
- unzip the to pictures dir
- find the first file and post the file uri
```


the file processing is done in the background using ExecutorService with thread pull.

the network call is done using Retrofit implementing the Api interface.

PerfUtil and SharedPrefernce to save the last imageIndex.

objects are injected with the service locator "Provider".

