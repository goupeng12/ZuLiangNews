package app.zuliangwang.zuliangnews.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import app.zuliangwang.zuliangnews.R;
import app.zuliangwang.zuliangnews.model.Contentlist;
import app.zuliangwang.zuliangnews.model.Root;
import app.zuliangwang.zuliangnews.network.ImageLoaderCache;

/**
 * Created by zuliangwang on 15/7/21.
 */
public class MainRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private Root root;
    private List<Contentlist> contentlists;
    private static final int TYPE_NOIMAGE = 1;
    private static final int TYPE_ONEIMAGE = 2;
    private static final int TYPE_THREEIMAGE = 3;
    private LayoutInflater mLayoutInflater;
    private String title;

    public class NoPictureItemViewHolder extends RecyclerView.ViewHolder {
        private TextView noImageItemTitle;

        public NoPictureItemViewHolder(View itemView) {
            super(itemView);
            noImageItemTitle = (TextView) itemView.findViewById(R.id.noimage_item_title);

            noImageItemTitle.setText(title);
        }
    }

    public class OnePictureItemViewHolder extends RecyclerView.ViewHolder {


        private ImageView oneImageItemImage;
        private TextView oneImageItemTitle;

        public OnePictureItemViewHolder(View itemView) {
            super(itemView);
            oneImageItemImage = (ImageView) itemView.findViewById(R.id.oneimage_item_imgae);
            oneImageItemTitle = (TextView) itemView.findViewById(R.id.oneimage_item_title);

            oneImageItemTitle.setText(title);

        }
    }

    public class ThreeImageItemViewHolder extends RecyclerView.ViewHolder {
        private TextView threeImageItemTitle;
        private ImageView threeImageItemImage1;
        private ImageView threeImageItemImage2;
        private ImageView threeImageItemImage3;
        private List<ImageView> threeImageItemImageList;

        public ThreeImageItemViewHolder(View itemView) {
            super(itemView);
            threeImageItemTitle = (TextView) itemView.findViewById(R.id.threeimage_item_title);
            threeImageItemImage1 = (ImageView) itemView.findViewById(R.id.threeimage_item_image1);
            threeImageItemImage2 = (ImageView) itemView.findViewById(R.id.threeimage_item_image2);
            threeImageItemImage3 = (ImageView) itemView.findViewById(R.id.threeimage_item_image3);

            threeImageItemTitle.setText(title);

            threeImageItemImageList = new ArrayList<ImageView>();
            threeImageItemImageList.add(threeImageItemImage1);
            threeImageItemImageList.add(threeImageItemImage2);
            threeImageItemImageList.add(threeImageItemImage3);
        }
    }

    @Override
    public int getItemCount() {
        return root.showapi_res_body.pagebean.contentlist.size();
//        return 5;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if ((contentlists.get(position).imageurls) != null) {

            RequestQueue queue = Volley.newRequestQueue(mContext);
            ImageLoader imageLoader = new ImageLoader(queue,new ImageLoaderCache());
            if (holder instanceof OnePictureItemViewHolder){
                ImageLoader.ImageListener listener = ImageLoader.getImageListener(((OnePictureItemViewHolder) holder).oneImageItemImage,R.mipmap.ic_launcher,R.mipmap.ic_launcher);
                imageLoader.get(contentlists.get(position).imageurls.get(0).getUrl(),listener);
            }
            if (holder instanceof ThreeImageItemViewHolder){
                for (int i=0;i<3;i++){
                    Log.d("pxzcl",contentlists.get(position).imageurls.size()+"");
                ImageLoader.ImageListener listener = ImageLoader.getImageListener(((ThreeImageItemViewHolder) holder).threeImageItemImageList.get(i),R.mipmap.ic_launcher,R.mipmap.ic_launcher);
                    imageLoader.get(contentlists.get(position).imageurls.get(i).getUrl(),listener);

                }
            }

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_NOIMAGE:
                return new NoPictureItemViewHolder(mLayoutInflater.inflate(R.layout.item_news_noimage_viewholder, parent, false));
            case TYPE_ONEIMAGE:
                return new OnePictureItemViewHolder(mLayoutInflater.inflate(R.layout.item_news_viewholder, parent, false));
            default:
                return new ThreeImageItemViewHolder(mLayoutInflater.inflate(R.layout.item_news_threeimage_viewholder, parent, false));
        }
    }

    @Override
    public int getItemViewType(final int position) {
        contentlists = root.showapi_res_body.pagebean.contentlist;
        title = contentlists.get(position).getTitle();

        if (contentlists.get(position).imageurls != null)
            switch (contentlists.get(position).imageurls.size()) {
                case 0:
                    return TYPE_NOIMAGE;
                case 1:
                    return TYPE_ONEIMAGE;
                case 2:
                    return TYPE_ONEIMAGE;
                default:
                    Log.d("pxz","p"+contentlists.get(position).imageurls.size());
                    return TYPE_THREEIMAGE;
            }
        else return TYPE_NOIMAGE;

    }

    public MainRecyclerViewAdapter(Context context) {
        super();
        this.root = root;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setRoot(Root root) {
        this.root = root;
    }
}
