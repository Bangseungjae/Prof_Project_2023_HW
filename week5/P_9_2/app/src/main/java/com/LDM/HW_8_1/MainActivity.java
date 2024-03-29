//실습 8_1

package com.LDM.HW_8_1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity2";

    private ImageButton ibZoomIn, ibZoomOut, ibRotate, ibBright, ibDark, ibGray,
            ibBlurring, ibEmbossing;
    private MyGraphicView graphicView;
    private static float scaleX = 1, scaleY = 1;
    private static float angle = 0;
    private static float color = 1;
    private static float saturation = 1;
    private static BlurMaskFilter bMask;
    private static EmbossMaskFilter eMask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("2019038024 이동민");

        LinearLayoutCompat pictureLayout = findViewById(R.id.pictureLayout);
        graphicView = new MyGraphicView(this);
        pictureLayout.addView(graphicView);

        init();
        initLr();
    }

    public void init(){
        ibZoomIn = findViewById(R.id.ibZoomIn);
        ibZoomOut = findViewById(R.id.ibZoomOut);
        ibRotate = findViewById(R.id.ibRotate);
        ibBright = findViewById(R.id.ibBright);
        ibDark = findViewById(R.id.ibDark);
        ibGray = findViewById(R.id.ibGray);
        ibBlurring = findViewById(R.id.ibBlurring);
        ibEmbossing = findViewById(R.id.ibEmbossing);
        ibEmbossing = findViewById(R.id.ibEmbossing);
    }

    public void initLr(){
        // 확대 버튼을 클릭할 때마다 축척 전역변수가 0.2씩 증가함
        ibZoomIn.setOnClickListener(v -> {
            scaleX = scaleX + 0.2f;
            scaleY = scaleY + 0.2f;
            // 확대를 위해서 onDraw( ) 메서드를 다시 호출해야하는데
            // 뷰의 invalidate( ) 메서드는 onDraw( )를 자동으로 호출함
            graphicView.invalidate();
        });
        // 축소 버튼을 클릭할 때마다 축척 전역변수가 0.2씩 감소함
        ibZoomOut.setOnClickListener(v -> {
            scaleX = scaleX - 0.2f;
            scaleY = scaleY - 0.2f;
            graphicView.invalidate();
        });
        // 회전하기 버튼을 클릭할 때마다 회전각도가 20도씩 증가함
        ibRotate.setOnClickListener(v -> {
            angle = angle + 20;
            graphicView.invalidate();
        });
        // 밝게하기 버튼을 클릭할 때마다 밝기가 0.2배씩 증가함
        ibBright.setOnClickListener(v -> {
            color = color + 0.2f;
            graphicView.invalidate();
        });
        // 어둡게하기 버튼을 클릭할 때마다 밝기가 0.2배씩 감소함
        ibDark.setOnClickListener(v -> {
            color = color - 0.2f;
            graphicView.invalidate();
        });
        // 회색영상 버튼을 클릭할 때마다 채도가 1이면 0으로, 0이면 1로 변경
        ibGray.setOnClickListener(v -> {
            if(saturation == 0) {
                saturation = 1;
            } else {
                saturation = 0;
            }
            graphicView.invalidate();
        });
        // 블러링 버튼을 클릭하면 SOLID 블러링 적용
        ibBlurring.setOnClickListener(v -> {
            bMask = new BlurMaskFilter(30, BlurMaskFilter.Blur.SOLID);
            graphicView.invalidate();
        });
        // 엠보싱 버튼을 클릭하면 빛 방향 변경
        ibEmbossing.setOnClickListener(v -> {
            eMask = new EmbossMaskFilter(new float[] {3, 3, 10}, 0.8f, 7, 10);
            graphicView.invalidate();
        });
    }

    // MyGraphicView 클래스를 정의
    private static class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // 화면(뷰)의 중앙을 구하고, 전역변수에 설정된 값으로 캔버스의 축척을 설정
            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;
            canvas.scale(scaleX, scaleY, cenX, cenY);
            // 전역변수에 설정된 각도로 캔버스를 회전시킴
            canvas.rotate(angle, cenX, cenY);

            Paint paint = new Paint();
            float[] array = { color, 0, 0, 0, 0,
                    0, color, 0, 0, 0,
                    0, 0, color, 0, 0,
                    0, 0, 0, 1, 0,};

            ColorMatrix cm = new ColorMatrix(array);
            // setSaturation( ) 메서드가 실행되면 위에 설정된 ColorMatrix
            if(saturation == 0) {
                cm.setSaturation(saturation);
            }

            paint.setColorFilter(new ColorMatrixColorFilter(cm));

            Bitmap picture =
                    BitmapFactory.decodeResource(getResources(), R.drawable.umbrella);

            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;

            paint.setMaskFilter(bMask);

            paint.setMaskFilter(eMask);

            canvas.drawBitmap(picture, picX, picY, paint);

            picture.recycle();
        }
    }
}