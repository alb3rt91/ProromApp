package com.example.prorom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private Integer[] mThumbIds;

    public ImageAdapter(Context context, Integer[] thumbIds) {
        mContext = context;
        mThumbIds = thumbIds;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflamos el layout para cada imagen
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        // Establecemos la imagen en el ImageView
        holder.imageView.setImageResource(mThumbIds[position]);

        // Añadimos el listener para ampliar la imagen al hacer clic
        holder.imageView.setOnClickListener(v -> {
            showFullImage(mThumbIds[position]);
        });
    }

    @Override
    public int getItemCount() {
        return mThumbIds.length;
    }

    // Método para mostrar la imagen ampliada
    private void showFullImage(int imageResId) {
        ImageDialogFragment dialog = ImageDialogFragment.newInstance(imageResId);
        dialog.show(((FragmentActivity) mContext).getSupportFragmentManager(), "imageDialog");
    }

    // ViewHolder que representa una imagen
    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
