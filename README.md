# DZI Scaling Service
The service will extract PNG or JPEG image with given parameters (x, y, width, height, scale) from a DZI pyramid stored at CSCS.

Usecases:
* Get downscaled image. 
* Access image tile by tile with user defined tile size.

The example URLs:
* http://dzi-scaler-service-pyramid-services.apps-dev.hbp.eu/scale/?dzi=https://object.cscs.ch/v1/AUTH_08c08f9f119744cbbf77e216988da3eb/imgsvc-890b727c-f3b5-624f-028a-4f6d35acce18/D1R_P70_F_C60_s001.tif/D1R_P70_F_C60_s001.dzi&scale=0.1&format=jpg
</br>This URL will return 10% scaled down original image as JPEG.
* http://dzi-scaler-service-pyramid-services.apps-dev.hbp.eu/region/?dzi=https://object.cscs.ch/v1/AUTH_08c08f9f119744cbbf77e216988da3eb/imgsvc-890b727c-f3b5-624f-028a-4f6d35acce18/D1R_P70_F_C60_s001.tif/D1R_P70_F_C60_s001.dzi&x=3000&y=2500&width=500&height=500
</br>This URL will return a PNG image containing the subimage taken at (x=3000, y=2500) with width=500 and height=500.

Known limitation: the resulting image can’t be larger than 2GB.
