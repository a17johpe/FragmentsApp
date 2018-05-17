package org.brohede.marcus.fragmentsapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VeryDetailedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VeryDetailedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VeryDetailedFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_NAME = "mountain_name";
    public static final String ARG_LOCATION = "mountain_location";
    public static final String ARG_HEIGHT = "mountain_height";

    // TODO: Rename and change types of parameters
    private String mMountain_name;
    private String mMountain_location;
    private String mMountain_height;

    private OnFragmentInteractionListener mListener;

    public VeryDetailedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VeryDetailedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VeryDetailedFragment newInstance(String param1, String param2, String param3) {
        VeryDetailedFragment fragment = new VeryDetailedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, param1);
        args.putString(ARG_LOCATION, param2);
        args.putString(ARG_HEIGHT, param3);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMountain_name = getArguments().getString(ARG_NAME);
            mMountain_location = getArguments().getString(ARG_LOCATION);
            mMountain_height = getArguments().getString(ARG_HEIGHT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_very_detailed, container, false);

        TextView mountainNames = (TextView) view.findViewById(R.id.textview_mountainname);
        TextView mountainLocations = (TextView) view.findViewById(R.id.textview_mountainlocation);
        TextView mountainHeight = (TextView) view.findViewById(R.id.textview_mountainheight);

        mountainNames.setText(mMountain_name);
        mountainLocations.setText(mMountain_location);
        mountainHeight.setText(mMountain_height);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
