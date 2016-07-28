package ggbtech.muralonline;

/**
 * Created by AEDI on 17/05/16.
 */
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AvisoAdapter extends BaseAdapter {
    private Context context;
    private List<Aviso> list;

    public AvisoAdapter(Context context, List<Aviso> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0).getId();
    }

    @Override
    public View getView(int position, View arg1, ViewGroup arg2) {
        final int auxPosition = position;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.cardview_aviso_novo, null);

        int foto = list.get(position).getImagem();
        ImageView imagem = (ImageView) layout.findViewById(R.id.foto);

        switch(foto){
            case 1:imagem.setImageResource(R.drawable.catequetica);break;
            case 2:imagem.setImageResource(R.drawable.emcri);break;
            case 3:imagem.setImageResource(R.drawable.familiar);break;
            case 4:imagem.setImageResource(R.drawable.juventude);break;
            case 5:imagem.setImageResource(R.drawable.logo_ufpa);break;
            case 6:imagem.setImageResource(R.drawable.menor);break;
        }

        TextView titulo = (TextView) layout.findViewById(R.id.titulo);
        titulo.setText(list.get(position).getTitulo());
        TextView evento = (TextView) layout.findViewById(R.id.evento);
        evento.setText(list.get(position).getEvento());
        TextView data = (TextView) layout.findViewById(R.id.data);
        data.setText(list.get(position).getData());
        TextView hora = (TextView) layout.findViewById(R.id.hora);
        hora.setText("às "+ list.get(position).getHora());
        TextView observacao = (TextView) layout.findViewById(R.id.obs);
        observacao.setText(list.get(position).getObservacao());
        TextView contato = (TextView) layout.findViewById(R.id.contato);
        contato.setText(list.get(position).getContato());




        /*
        TextView tv = (TextView) layout.findViewById(R.id.nome);
        tv.setText(list.get(position).getNome());


        Button editarBt = (Button) layout.findViewById(R.id.editar);
        editarBt.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, NewUserActivity.class);
                intent.putExtra("nome", list.get(auxPosition).getNome());
                intent.putExtra("email", list.get(auxPosition).getEmail());
                intent.putExtra("id", list.get(auxPosition).getId());
                context.startActivity(intent);
            }
        });*/

        ImageView deletarBt = (ImageView) layout.findViewById(R.id.delete);
        deletarBt.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                BD bd = new BD(context);
                                bd.deletar(list.get(auxPosition));
                                layout.setVisibility(View.GONE);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Voce tem certeza?").setPositiveButton("SIM", dialogClickListener)
                        .setNegativeButton("NAO", dialogClickListener).show();

            }
        });

        return layout;
    }

}
