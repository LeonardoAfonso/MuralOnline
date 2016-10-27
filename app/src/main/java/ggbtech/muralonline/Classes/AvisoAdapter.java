package ggbtech.muralonline.Classes;

/**
 * Created by AEDI on 17/05/16.
 */
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ggbtech.muralonline.DB.BD;
import ggbtech.muralonline.R;

public class AvisoAdapter extends BaseAdapter{
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
    public CardView getView(int position, View arg1, ViewGroup arg2) {
        final int auxPosition = position;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final CardView layout = (CardView)inflater.inflate(R.layout.aviso_cv,null);

        int foto = list.get(position).getImagem();
        ImageView imagem = (ImageView) layout.findViewById(R.id.foto);

        switch(foto){
            case 1:imagem.setImageResource(R.drawable.logo_pastoral_catequetica);break;
            case 2:imagem.setImageResource(R.drawable.logo_pastoral_dizimo);break;
            case 3:imagem.setImageResource(R.drawable.logo_pastoral_familiar);break;
            case 4:imagem.setImageResource(R.drawable.logo_jumire);break;
            case 5:imagem.setImageResource(R.drawable.logo_pastoral_crianca);break;
            case 6:imagem.setImageResource(R.drawable.logo_pastoral_menor);break;
            case 7:imagem.setImageResource(R.drawable.logo_pastoral_pessoa_idosa);break;
            case 8:imagem.setImageResource(R.drawable.logo_emcri);break;
            case 9:imagem.setImageResource(R.drawable.logo_aic);break;
            case 10:imagem.setImageResource(R.drawable.logo_pastoral_social);break;
            case 11:imagem.setImageResource(R.drawable.logo_apostolado_oracao);break;
            case 12:imagem.setImageResource(R.drawable.logo_legiao_maria);break;
            case 13:imagem.setImageResource(R.drawable.logo_ecc);break;
            case 14:imagem.setImageResource(R.drawable.logo_escoteiros);break;
            case 15:imagem.setImageResource(R.drawable.logo_liturgia);break;
            case 16:imagem.setImageResource(R.drawable.logo_navegantes);break;
            case 17:imagem.setImageResource(R.drawable.logo_aparecida);break;
            case 18:imagem.setImageResource(R.drawable.logo_santa_rita);break;
            case 19:imagem.setImageResource(R.drawable.logo_sagrada_familia);break;
            case 20:imagem.setImageResource(R.drawable.logo_imaculado_coracao_maria);break;
            case 21:imagem.setImageResource(R.drawable.logo_jc);break;
            case 22: imagem.setImageResource(R.drawable.logo_guarda);break;
            case 23:imagem.setImageResource(R.drawable.logo_ministros_eucaristia);break;
            case 24:imagem.setImageResource(R.drawable.logo_servidores_altar);break;
            case 25:imagem.setImageResource(R.drawable.logo_pastoral_batismo);break;

            case 50:imagem.setImageResource(R.drawable.icon_secretaria);break;
            case 51:imagem.setImageResource(R.drawable.icon_missa);break;
            case 52:imagem.setImageResource(R.drawable.icon_adoracao);break;
            case 53:imagem.setImageResource(R.drawable.icon_nossa_senhora);break;
        }

        TextView titulo = (TextView) layout.findViewById(R.id.titulo);
        titulo.setText(list.get(position).getTitulo());
        TextView evento = (TextView) layout.findViewById(R.id.evento);
        evento.setText(list.get(position).getEvento());
        TextView local = (TextView)  layout.findViewById(R.id.local);
        local.setText(list.get(position).getLocal());
        TextView data = (TextView) layout.findViewById(R.id.data);
        data.setText(list.get(position).getData());
        TextView hora = (TextView) layout.findViewById(R.id.hora);
        hora.setText("às "+ list.get(position).getHora());
        TextView observacao = (TextView) layout.findViewById(R.id.obs);
        observacao.setText(list.get(position).getObservacao());
        TextView contato = (TextView) layout.findViewById(R.id.contato);
        contato.setText(list.get(position).getContato());

        ImageView deletarBt = (ImageView) layout.findViewById(R.id.delete);
        deletarBt.setColorFilter(Color.GRAY);
        deletarBt.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_NEGATIVE://valor invertido POSITIVE
                                //Yes button clicked
                                BD bd = new BD(context);
                                bd.deletar(list.get(auxPosition));
                                layout.setVisibility(View.GONE);
                                Toast.makeText(context, "Aviso excluido", Toast.LENGTH_LONG).show();
                                break;

                            case DialogInterface.BUTTON_POSITIVE://valor invertido NEGATIVE
                                //No button clicked
                                break;
                        }
                    }
                };
                //invertendo os valores dos botoes
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Voce tem certeza?").setPositiveButton("NÃO", dialogClickListener)
                        .setNegativeButton("SIM", dialogClickListener).show();
            }
        });

        return layout;
    }

}
