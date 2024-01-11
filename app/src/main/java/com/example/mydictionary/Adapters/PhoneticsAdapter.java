package com.example.mydictionary.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydictionary.Models.Phonetics;
import com.example.mydictionary.R;
import com.example.mydictionary.ViewHolders.PhoneticViewHolder;

import java.util.List;

public class PhoneticsAdapter extends RecyclerView.Adapter<PhoneticViewHolder> {

    private Context context;
    private List<Phonetics> phoneticsList;

    public PhoneticsAdapter(Context context, List<Phonetics> phoneticsList) {
        this.context = context;
        this.phoneticsList = phoneticsList;
    }

    @NonNull
    @Override
    public PhoneticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhoneticViewHolder(LayoutInflater.from(context).inflate(R.layout.phonetic_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneticViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView_phonetic.setText(phoneticsList.get(position).getText());


        holder.imageButton_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Clicked");
                System.out.println(phoneticsList.get(position).getAudio());
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioAttributes(
                        new AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .build()
                );
                try{
                    mediaPlayer.setDataSource(phoneticsList.get(position).getAudio());
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(v->{
                        System.out.println("Started");
                        v.start();
                    });
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(context, "Can not play audio!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return phoneticsList.size();
    }
}
