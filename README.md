#ImageFetcher

Allows you to pass in a URL and it will find the primary image and download it for you. Perfect for thumnail generation or image scraping!

Currently supports:
* Any major image format
* Imgur
* Deviant Art
* Gfycat
* Giphy
* Twitter

and any other website on the internet!

Using it is a simple as:
```java

ImageFetcher f = new ImageFetcher();
FetchResult fr = f.fetch(new URL("https://imgur.com/gallery/aKaOqIh"));

FileUtils.writeByteArrayToFile(new File("fetched.jpg"), b.getImage());

```
Adding custom host support is as simple as:
```java

HostFetcher hf = new HostFetcher(){
  ...
};

HostFetcherFactory.addCustomHost("arctro.com", hf);

```

This isn't necessary however! ImageFetcher can scrape any unknown website and find the correct image to download!
