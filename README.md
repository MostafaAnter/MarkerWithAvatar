# MarkerWithAvatar
create google map marker with custom avatar

<img src="https://raw.githubusercontent.com/MostafaAnter/MarkerWithAvatar/master/device-2020-05-23-201712.png" width="300">

# How to use

    CustomMarker.Builder()
            .context(this)
            .avatar("https://pbs.twimg.com/profile_images/1248572477439016960/0EXyKwBK_400x400.jpg") //your avatar url
            .googleMap(mMap)
            .lat(29.9780115) // your latitude
            .long(31.13215)  // your longitude 
            .setMarkerBackground(R.drawable.black_marker) // for change marker background
            .build()

# Download

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.MostafaAnter:MarkerWithAvatar:1.0'
	}
