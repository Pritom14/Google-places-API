package google.places.android;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<GetterSetter> {

	private static final String TAG = CustomAdapter.class.getSimpleName();

	Context context;
	ArrayList<GetterSetter> myList;
	int textViewResourceId;

	public CustomAdapter(Context context, int textViewResourceId, List<GetterSetter> objects) {
		super(context, textViewResourceId, objects);
		Log.d(TAG, "CustomAdapter: constructor called, objects size=" + (objects != null ? objects.size() : 0));
		this.myList = (ArrayList<GetterSetter>) objects;
		this.context = context;
		this.textViewResourceId = textViewResourceId;

	}

	@Override public View getView(int position, View convertView, ViewGroup parent) {
		Log.d(TAG, "getView: position=" + position);
		View row = convertView;
		TextViewHolder holder = null;

		if (row == null) {
			Log.d(TAG, "getView: convertView is null, inflating new row view");
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(textViewResourceId, parent, false);

			holder = new TextViewHolder();
			holder.name = (TextView) row.findViewById(R.id.name);
			holder.rating = (TextView) row.findViewById(R.id.rating);
			holder.ratingText = (TextView) row.findViewById(R.id.ratingRext);
			row.setTag(holder);
		} else {
			Log.d(TAG, "getView: reusing convertView, fetching holder from tag");
			holder = (TextViewHolder) row.getTag();
		}

		holder.name.setText(myList.get(position).name);
		holder.rating.setText(myList.get(position).rating);

		return row;
	}

	static class TextViewHolder {
		TextView name;
		TextView rating;
		TextView ratingText;
	}
}
