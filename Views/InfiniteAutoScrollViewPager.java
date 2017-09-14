package com.test.utils.views.infiniteviewpager;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * 工程名 ：ForManWorker
 * 包名 ：com.ioi.library.view
 */

public class InfiniteAutoScrollViewPager extends RelativeLayout {
    private Context mContext;
    private ViewPager viewPager;
    private List<View> viewList;
    private ImageView[] imageVernier;
    private AutoRoll autoRoll;
    private int[] mImageVer;
    //private TextView number;
    private OnItemClickListener onItemClickListener;
    //private boolean isTwo;

    public InfiniteAutoScrollViewPager(Context context) {
        this(context, null);
        mContext = context;
    }

    public InfiniteAutoScrollViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        mContext = context;
    }

    public InfiniteAutoScrollViewPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        viewPager = new ViewPager(context);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(viewPager, params);
        autoRoll = new AutoRoll(viewPager);
    }

    /*private ViewPager.OnPageChangeListener TextOnChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float v, int i1) {
            for (ImageView ignored : imageVernier) {
                number.setText(String.valueOf(position % imageVernier.length + 1) + "/" + viewList.size());
            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int position) {

        }
    };*/

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float v, int i1) {
            /*for (int i = 0; i < imageVernier.length; i++) {
                imageVernier[position % imageVernier.length].setBackgroundResource(mImageVer[0]);
                if (position % imageVernier.length != i) {
                    imageVernier[i].setBackgroundResource(mImageVer[1]);
                }
            }*/
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int position) {

        }
    };

    /**
     * Important: Managing auto scroll and manually scroll conflict issue for thread lock
     */
    private OnTouchListener onTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    stopThread();
                    break;
                case MotionEvent.ACTION_UP:
                    if (autoRoll.getAutoTurningTime() != 0 && viewList.size() > 1) {
                        startThread(autoRoll.getAutoTurningTime());
                    }
                    break;
            }
            return false;
        }
    };

    /**
     * viewpager setAdapter
     */
    public void setAdapter() {
        PagerAdapter mAdapter = new AdvPagerAdapter(viewList);
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(-1);
        viewPager.setCurrentItem(viewList.size() * 500);
    }

    /*public void addOnPageChangeListener(TextView number) {
        viewPager.addOnPageChangeListener(TextOnChangeListener);
    }*/

    public void addOnPageChangeListener() {
        viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    /*private void initDot(ViewGroup group, int[] imageVer) {
        if (group != null) {
            mImageVer = imageVer;
            if (isTwo) {
                imageVernier = new ImageView[viewList.size() / 2];
            } else
                imageVernier = new ImageView[viewList.size()];

            //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(10, 10);
            //layoutParams.setMargins(5, 0, 5, 0);
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(DeviceUtils.dpToPx(mContext, 4), DeviceUtils.dpToPx(mContext, 4));
            params.leftMargin = 4;
            params.rightMargin = 4;
            for (int i = 0; i < imageVernier.length; i++) {
                ImageView vernier = new ImageView(getContext());
                //vernier.setLayoutParams(layoutParams);
                imageVernier[i] = vernier;
                if (i == 0) {
                    imageVernier[i].setBackgroundResource(imageVer[0]);
                } else {
                    imageVernier[i].setBackgroundResource(imageVer[1]);
                }
                group.addView(imageVernier[i], params);
            }
        }

        setAdapter();
        if (group != null) {
            addOnPageChangeListener();
            setOnTouchListener();
        }
    }*/

    /**
     * @param number
     * @param image
     */
    /*public void initImage(TextView number, int[] image) {
        this.number = number;

        viewList = new ArrayList<>();
        for (int anImage1 : image) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(anImage1);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewList.add(imageView);
        }
        if (viewList.size() == 2) {
            isTwo = true;
            for (int anImage : image) {
                ImageView imageView = new ImageView(getContext());
                imageView.setImageResource(anImage);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                viewList.add(imageView);
            }
        }
        if (isTwo) {
            imageVernier = new ImageView[viewList.size() / 2];
        } else
            imageVernier = new ImageView[viewList.size()];

        setAdapter();
        addOnPageChangeListener(number);
        setOnTouchListener();
    }*/

    /**
     * @param number
     * @param url
     */
    /*public void initImage(TextView number, String[] url) {
        this.number = number;

        viewList = new ArrayList<>();
        for (String anUrl : url) {
            ImageView imageView = new ImageView(getContext());
            Glide.with(mContext).load(anUrl).placeholder(R.drawable.img_placeholder_150).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewList.add(imageView);
        }
        if (viewList.size() == 2) {
            isTwo = true;
            for (String anUrl : url) {
                ImageView imageView = new ImageView(getContext());
                Glide.with(mContext).load(anUrl).placeholder(R.drawable.img_placeholder_150).into(imageView);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                viewList.add(imageView);
            }
        }
        if (isTwo) {
            imageVernier = new ImageView[viewList.size() / 2];
        } else
            imageVernier = new ImageView[viewList.size()];

        setAdapter();
        addOnPageChangeListener(number);
        setOnTouchListener();
    }*/


    /**
     * @param group
     * @param image
     * @param imageVer
     */
    /*public void initImage(ViewGroup group, int[] image, int[] imageVer) {
        viewList = new ArrayList<>();

        for (int anImage : image) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(anImage);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewList.add(imageView);
        }
        if (viewList.size() == 2) {
            isTwo = true;
            for (int anImage : image) {
                ImageView imageView = new ImageView(getContext());
                imageView.setImageResource(anImage);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                viewList.add(imageView);
            }
        }
        initDot(group, imageVer);
    }*/

    /**
     */
    /*public void initImage(ViewGroup group, List<String> url, int[] imageVer) {
        viewList = new ArrayList<>();
        for (String anUrl : url) {
            ImageView imageView = new ImageView(getContext());
            Glide.with(mContext).load(anUrl).placeholder(R.drawable.img_placeholder_150).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewList.add(imageView);
        }
        if (viewList.size() == 2) {
            isTwo = true;
            for (String anUrl : url) {
                ImageView imageView = new ImageView(getContext());
                Glide.with(mContext).load(anUrl).placeholder(R.drawable.img_placeholder_150).into(imageView);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                viewList.add(imageView);
            }
        }
        initDot(group, imageVer);
    }*/

    /*public void initImage1(ViewGroup group, List<String> url, int[] imageVer) {
        viewList = new ArrayList<>();
        for (String anUrl : url) {
            ImageView imageView = new ImageView(getContext());
            //imageView.setImageResource(Integer.parseInt(anUrl));
            Glide.with(mContext).load(anUrl).placeholder(R.drawable.img_placeholder_150).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewList.add(imageView);
        }
        if (viewList.size() == 2) {
            isTwo = true;
            for (String anUrl : url) {
                ImageView imageView = new ImageView(getContext());
                //imageView.setImageResource(Integer.parseInt(anUrl));
                Glide.with(mContext).load(anUrl).placeholder(R.drawable.img_placeholder_150).into(imageView);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                viewList.add(imageView);
            }
        }
        initDot(group, imageVer);
    }*/

    //Pass model for image : Rahul
    //Use this function for infinite view pager
    /*public void initImage(List<GetFeaturesModel> featureAdsPagerList) {
        viewList = new ArrayList<>();
        for (GetFeaturesModel pagerModel : featureAdsPagerList) {
            String anUrl = pagerModel.bannerImage;
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            Glide.with(mContext).load(anUrl).dontAnimate().into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewList.add(imageView);
        }

        setAdapter();
        addOnPageChangeListener();
        setOnTouchListener();
    }*/
    public void setOnTouchListener() {
        viewPager.setOnTouchListener(onTouchListener);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * PagerAdapter
     */
    public class AdvPagerAdapter extends PagerAdapter {

        private List<View> viewList;

        public AdvPagerAdapter(List<View> viewList) {
            this.viewList = viewList;
        }

        @Override
        public int getCount() {
            if (viewList.size() <= 1) {
                return viewList.size();
            } else
                return Integer.MAX_VALUE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = viewList.get(position % viewList.size());
            if (view.getParent() != null) {
                container.removeView(view);
            }

            //add listeners here if necessary
            if (onItemClickListener != null) view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*if (isTwo)
                        onItemClickListener.onItemClick(viewPager.getCurrentItem() % viewList.size() < 2 ?
                                viewPager.getCurrentItem() % viewList.size() : viewPager.getCurrentItem() % viewList.size() % 2);
                    else*/
                    onItemClickListener.onItemClick(viewPager.getCurrentItem() % viewList.size());
                }
            });

            container.addView(view);
            return view;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //container.removeView(viewList.get(position%viewList.size()));
        }
    }

    public void startThread(long autoTurningTime) {
        if (autoTurningTime != 0 && viewList.size() > 1) {
            autoRoll.start(autoTurningTime);
            autoRoll.setAutoTurningTime(autoTurningTime);
        } else
            autoRoll.setIsLooping(false);
    }

    public void stopThread() {
        if (autoRoll.isLooping()) {
            autoRoll.stop();
        }
    }

    public class AutoRoll implements Runnable {
        private ViewPager viewPager;
        private Handler handler;
        private boolean isLooping;
        private long autoTurningTime;

        public boolean isLooping() {
            return isLooping;
        }

        public void setIsLooping(boolean isLooping) {
            this.isLooping = isLooping;
        }

        public AutoRoll(ViewPager viewPager) {
            this.viewPager = viewPager;
            handler = new Handler();
        }

        public long getAutoTurningTime() {
            return autoTurningTime;
        }

        public void setAutoTurningTime(long autoTurningTime) {
            this.autoTurningTime = autoTurningTime;
        }

        private void start(long autoTurningTime) {
            if (isLooping) {
                stop();
            }
            isLooping = true;
            handler.postDelayed(this, autoTurningTime);
        }

        private void stop() {
            isLooping = false;
            handler.removeCallbacks(this);
        }

        @Override
        public void run() {
            if (isLooping) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                handler.postDelayed(this, autoTurningTime);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}