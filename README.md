# ex1

file stracture:
```
/
|-di
| |-- provider
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
-fetch the image by:
--get the nextImage url
--fetch the file using retrofit.
--save the file to the files dir
--delete the picture dir
--nzip the to pictures dir
--tke the first file and post the file uri

the fetch file is done in the background using ExecutorService thread pull.

the network call is done with Retrofit implementing the Api interface.

PerfUtil and SharedPrefernce to save the last imageIndex.

objects are injected by the service locator "Provider".

