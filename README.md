# DZI Scaling Service
The service will extract PNG or JPEG image with given parameters (x, y, width, height, scale) from a DZI pyramid stored at CSCS.

Usecases:
* Get downscaled image. 
* Access image tile by tile with user defined tile size.

The example URLs:
* https://dziss.apps-dev.hbp.eu/scale/?dzi=https://object.cscs.ch/v1/AUTH_08c08f9f119744cbbf77e216988da3eb/imgsvc-13da6792-b4a0-4369-8312-107b8ea57986/D1R_P70_F_C60_s001.tif/D1R_P70_F_C60_s001.dzi&scale=0.01&format=jpg
</br>This URL will return 1% scaled down original image as JPEG.
* https://dziss.apps-dev.hbp.eu/region/?dzi=https://object.cscs.ch/v1/AUTH_08c08f9f119744cbbf77e216988da3eb/imgsvc-13da6792-b4a0-4369-8312-107b8ea57986/D1R_P70_F_C60_s001.tif/D1R_P70_F_C60_s001.dzi&x=3000&y=2500&width=500&height=500
</br>This URL will return a PNG image containing the subimage taken at (x=3000, y=2500) with width=500 and height=500.

Known limitation: the resulting image can’t be larger than 2GB.
