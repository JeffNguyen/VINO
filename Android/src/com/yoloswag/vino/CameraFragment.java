package com.yoloswag.vino;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class CameraFragment extends Fragment {
    
    protected static final String TAG = null;

	public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(0); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }
    
    private Camera mCamera;
    private CameraPreview mPreview;

    @SuppressWarnings("deprecation")
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Create an instance of Camera
        mCamera = getCameraInstance();
        
        Camera.Parameters p = mCamera.getParameters();
        
        if (Integer.parseInt(Build.VERSION.SDK) >= 8)
        	mCamera.setDisplayOrientation(90);
        else
        {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            {
                p.set("orientation", "portrait");
                p.set("rotation", 90);
            }
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            {
                p.set("orientation", "landscape");
                p.set("rotation", 90);
            }
        }
        
        mCamera.setParameters(p);

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(getActivity(), mCamera);
        FrameLayout preview = (FrameLayout)getView().findViewById(R.id.camera_preview);
        preview.addView(mPreview);
    }
    
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    
    private PictureCallback mPicture = new PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

        	try
        	{
        		BitmapFactory.Options opts = new BitmapFactory.Options();
        		Bitmap bitmap= BitmapFactory.decodeByteArray(data, 0, data.length,opts);
        		bitmap = Bitmap.createScaledBitmap(bitmap, 480, 480, false);
        		
        		Matrix matrix = new Matrix();
        		matrix.postRotate(90);
        		bitmap = Bitmap.createBitmap(bitmap, 0, 0, 
        				bitmap.getWidth(), bitmap.getHeight(), 
        		                              matrix, true);
        		
        		ImageView imageView = (ImageView)getView().findViewById(R.id.image);
        		imageView.setImageBitmap(bitmap);
        		//CameraProjectActivity.image.setImageBitmap(bitmap);
        	}
        	catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        }
    };

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.camera, container, false);
        View button = rootView.findViewById(R.id.button_capture);
        
        View accept = rootView.findViewById(R.id.button_accept);
        final Button a = (Button)accept;
        a.setVisibility(View.INVISIBLE);
        
        View decline = rootView.findViewById(R.id.button_decline);
        final Button d = (Button)decline;
        d.setVisibility(View.INVISIBLE);
        
        final Button b = (Button) button;
        b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg1) {
				// TODO Auto-generated method stub
				// get an image from the camera

	            mCamera.takePicture(null, null, mPicture);
	            a.setVisibility(View.VISIBLE);
	            d.setVisibility(View.VISIBLE);
	            b.setVisibility(View.INVISIBLE);
			}
        });
        
        a.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg1) {
				//save picture
			}
        });
        
        d.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg1) {
				//take picture again
			}
        });  

        return rootView;
    }
}
    