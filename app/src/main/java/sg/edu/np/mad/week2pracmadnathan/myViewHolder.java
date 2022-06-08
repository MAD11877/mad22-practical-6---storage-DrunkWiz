package sg.edu.np.mad.week2pracmadnathan;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class myViewHolder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView txt;
    TextView stxt;

    public myViewHolder(View itemView) {
        super(itemView);
        txt = itemView.findViewById(R.id.textView12);
        stxt = itemView.findViewById(R.id.textView13);
        image = itemView.findViewById(R.id.imageView4);
    }
}
