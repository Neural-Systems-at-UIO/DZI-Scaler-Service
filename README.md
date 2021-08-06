# DZI Scaling Service
The service will extract PNG or JPEG image with given parameters (x, y, width, height, scale) from a DZI pyramid stored at CSCS.

Usecases:
* Get downscaled image. 
* Access image tile by tile with user defined tile size.

The example URLs:
* https://dziss.apps-dev.hbp.eu/index.html?dzi=https://object.cscs.ch/v1/AUTH_08c08f9f119744cbbf77e216988da3eb/imgsvc-890b727c-f3b5-624f-028a-4f6d35acce18/D1R_P70_F_C60_s001.tif/D1R_P70_F_C60_s001.dzi&scale=0.1&format=jpeg
</br>This URL will return 10% scaled down original image as JPEG.
* https://dziss.apps-dev.hbp.eu/index.html?dzi=https://object.cscs.ch/v1/AUTH_08c08f9f119744cbbf77e216988da3eb/imgsvc-890b727c-f3b5-624f-028a-4f6d35acce18/D1R_P70_F_C60_s001.tif/D1R_P70_F_C60_s001.dzi&x=100&y=150&width=200&height=250&scale=0.5&format=png
</br>This URL will return a PNG image containing the subimage taken at (x=100, y=150) with width=200 and height=250 scaled down 50% of the original image.

Known limitation: the resulting image can’t be larger than 2GB.
