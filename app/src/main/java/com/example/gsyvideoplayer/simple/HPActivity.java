package com.example.gsyvideoplayer.simple;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.gsyvideoplayer.R;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.listener.GSYVideoProgressListener;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * 直接横屏
 */
public class HPActivity extends AppCompatActivity {

    StandardGSYVideoPlayer videoPlayer;

    OrientationUtils orientationUtils;
    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hp_play);
        list=new ArrayList<>();
        list.add("https://img00.yuanxinkangfu.com/avthumb2/file/resource/201810/1540801620303.mp4");
        list.add("https://img00.yuanxinkangfu.com/avthumb2/file/resource/201810/1539069887275.mp4");
        list.add("http://img00.sun-hc.com/avthumb2/file/resource/20185/1525848029868.mp4");
        list.add("https://img00.yuanxinkangfu.com/file/resource/201812/1544843683102.mp4");
        list.add("https://img00.yuanxinkangfu.com/avthumb2/file/resource/201811/1543565142748.mp4");
        init();
    }

    private void init() {
        try {
            videoPlayer =  (StandardGSYVideoPlayer)findViewById(R.id.video_player);

            videoPlayer.setUp(list.get(0), true, "测试视频");

            //增加封面
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(R.mipmap.xxx1);
            videoPlayer.setThumbImageView(imageView);
            //增加title
            videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
            //设置返回键
            videoPlayer.getBackButton().setVisibility(View.VISIBLE);
            //设置旋转
            orientationUtils = new OrientationUtils(this, videoPlayer);
            orientationUtils.setEnable(false);
            //直接横屏
            orientationUtils.resolveByClick();
            videoPlayer.setIsTouchWiget(true);
            //设置返回按键功能
            videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            videoPlayer.startPlayLogic();

            click();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public  void click(){
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        videoPlayer.setVideoAllCallBack(new GSYSampleCallBack(){
            @Override
            public void onAutoComplete(String url, Object... objects) {
                super.onAutoComplete(url, objects);
                System.out.println(">]-------12");
            }
        });

        //重播
        findViewById(R.id.tv_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String source1 = "https://img00.yuanxinkangfu.com/avthumb2/file/resource/201810/1540801620303.mp4";
                videoPlayer.setUp(list.get(1), true, "测试视频2222");
                videoPlayer.startPlayLogic();
            }
        });
        //下一集
        findViewById(R.id.tv_tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String source1 = "https://img00.yuanxinkangfu.com/avthumb2/file/resource/201810/1540801620303.mp4";
                videoPlayer.setUp(list.get(0), true, "测试视频2222");
                videoPlayer.startPlayLogic();
            }
        });



    }
    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onBackPressed() {
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            videoPlayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        videoPlayer.setVideoAllCallBack(null);
        super.onBackPressed();
    }
}

//new VideoAllCallBack() {
//@Override
//public void onStartPrepared(String url, Object... objects) {
//        System.out.println(">]-------1");
//        }
//
//@Override
//public void onPrepared(String url, Object... objects) {
//        System.out.println(">]-------2");
//        }
//
//@Override
//public void onClickStartIcon(String url, Object... objects) {
//        System.out.println(">]-------3");
//        }
//
//@Override
//public void onClickStartError(String url, Object... objects) {
//        System.out.println(">]-------4");
//        }
//
//@Override
//public void onClickStop(String url, Object... objects) {
//        System.out.println(">]-------5");
//        }
//
//@Override
//public void onClickStopFullscreen(String url, Object... objects) {
//        System.out.println(">]-------6");
//        }
//
//@Override
//public void onClickResume(String url, Object... objects) {
//        System.out.println(">]-------7");
//        }
//
//@Override
//public void onClickResumeFullscreen(String url, Object... objects) {
//        System.out.println(">]-------8");
//        }
//
//@Override
//public void onClickSeekbar(String url, Object... objects) {
//        System.out.println(">]-------9");
//        }
//
//@Override
//public void onClickSeekbarFullscreen(String url, Object... objects) {
//        System.out.println(">]-------10");
//        }
//
//@Override
//public void onAutoComplete(String url, Object... objects) {
//        System.out.println(">]-------11");
//        }
//
//@Override
//public void onEnterFullscreen(String url, Object... objects) {
//        System.out.println(">]-------12");
//        }
//
//@Override
//public void onQuitFullscreen(String url, Object... objects) {
//        System.out.println(">]-------13");
//        }
//
//@Override
//public void onQuitSmallWidget(String url, Object... objects) {
//        System.out.println(">]-------14");
//        }
//
//@Override
//public void onEnterSmallWidget(String url, Object... objects) {
//        System.out.println(">]-------15");
//        }
//
//@Override
//public void onTouchScreenSeekVolume(String url, Object... objects) {
//        System.out.println(">]-------16");
//        }
//
//@Override
//public void onTouchScreenSeekPosition(String url, Object... objects) {
//        System.out.println(">]-------17");
//        }
//
//@Override
//public void onTouchScreenSeekLight(String url, Object... objects) {
//        System.out.println(">]-------18");
//        }
//
//@Override
//public void onPlayError(String url, Object... objects) {
//        System.out.println(">]-------19");
//        }
//
//@Override
//public void onClickStartThumb(String url, Object... objects) {
//        System.out.println(">]-------20");
//        }
//
//@Override
//public void onClickBlank(String url, Object... objects) {
//        System.out.println(">]-------21");
//        }
//
//@Override
//public void onClickBlankFullscreen(String url, Object... objects) {
//        System.out.println(">]-------22");
//        }
//        }
