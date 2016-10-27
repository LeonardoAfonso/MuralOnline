package ggbtech.muralonline.Classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.List;

import ggbtech.muralonline.R;


public class ParceiroAdapter extends BaseAdapter {
    private Context ctx;
    private List<Bitmap> list;

    public ParceiroAdapter(Context context, List<Bitmap> list){
        this.ctx = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.list.get(position).getGenerationId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        RelativeLayout rl = (RelativeLayout) inflater.inflate(R.layout.quadro_parceiro,null);

        ImageView parceiro1 = (ImageView) rl.findViewById(R.id.parceiro1);
        parceiro1.setImageBitmap((Bitmap) getItem(position));

        ImageView parceiro2 = (ImageView) rl.findViewById(R.id.parceiro2);
        parceiro2.setImageBitmap((Bitmap) getItem(position+1));

        return rl;
    }
}
