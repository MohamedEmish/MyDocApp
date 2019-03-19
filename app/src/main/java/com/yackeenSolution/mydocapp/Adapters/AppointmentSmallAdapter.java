package com.yackeenSolution.mydocapp.Adapters;

/*
   Last edit :: March 8,2019
   ALL DONE :)
 */

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yackeenSolution.mydocapp.Objects.Appointment;
import com.yackeenSolution.mydocapp.R;

public class AppointmentSmallAdapter extends ListAdapter<Appointment, AppointmentSmallAdapter.AppointmentSmallViewHolder> {
    private static final DiffUtil.ItemCallback<Appointment> DIFF_CALLBACK = new DiffUtil.ItemCallback<Appointment>() {
        @Override
        public boolean areItemsTheSame(Appointment oldItem, Appointment newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Appointment oldItem, Appointment newItem) {
            return oldItem.getDoctorName().equals(newItem.getDoctorName())
                    && oldItem.getClinicName().equals(newItem.getClinicName())
                    && oldItem.getClinicLocation().equals(newItem.getClinicLocation())
                    && oldItem.getId() == newItem.getId();
        }
    };
    private AppointmentSmallAdapter.OnItemCallClickListener callListener;
    private AppointmentSmallAdapter.OnItemCancelClickListener cancelListener;
    private AppointmentSmallAdapter.OnItemDirectionClickListener directionListener;


    public AppointmentSmallAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    public AppointmentSmallViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.appointment_small_item, parent, false);
        return new AppointmentSmallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AppointmentSmallViewHolder holder, int position) {
        String doctorName = getItem(position).getDoctorName();
        String bookingNumber = String.valueOf(getItem(position).getId());
        String speciality = getItem(position).getSpeciality();
        String facility = getMemberAt(position).getClinicName();
        String date = getMemberAt(position).getDateTime();
        String time = getMemberAt(position).getTime();

        holder.doctorName.setText(doctorName);
        holder.bookingNumber.setText(bookingNumber);
        holder.speciality.setText(speciality);
        holder.facility.setText(facility);
        holder.date.setText(date);
        holder.time.setText(time);
    }

    public Appointment getMemberAt(int position) {
        return getItem(position);
    }

    public void setOnItemCallClickListener(AppointmentSmallAdapter.OnItemCallClickListener listener) {
        this.callListener = listener;
    }

    public void setOnItemCancelClickListener(AppointmentSmallAdapter.OnItemCancelClickListener listener) {
        this.cancelListener = listener;
    }

    public void setOnItemDirectionClickListener(AppointmentSmallAdapter.OnItemDirectionClickListener listener) {
        this.directionListener = listener;
    }

    public interface OnItemCallClickListener {
        void onItemClick(Appointment appointment);
    }

    public interface OnItemCancelClickListener {
        void onItemClick(Appointment appointment);
    }

    public interface OnItemDirectionClickListener {
        void onItemClick(Appointment appointment);
    }

    public class AppointmentSmallViewHolder extends RecyclerView.ViewHolder {

        public TextView doctorName;
        public TextView bookingNumber;
        public TextView speciality;
        public TextView facility;
        public TextView date;
        public TextView time;
        public TextView direction;
        public ImageView call;
        public Button cancel;


        public AppointmentSmallViewHolder(View itemView) {
            super(itemView);

            doctorName = itemView.findViewById(R.id.appointment_small_doctor_name);
            bookingNumber = itemView.findViewById(R.id.appointment_small_booking_number);
            speciality = itemView.findViewById(R.id.appointment_small_speciality);
            facility = itemView.findViewById(R.id.appointment_small_facility);
            date = itemView.findViewById(R.id.appointment_small_date);
            time = itemView.findViewById(R.id.appointment_small_time);
            call = itemView.findViewById(R.id.appointment_small_call);
            cancel = itemView.findViewById(R.id.appointment_small_cancel_req);
            direction = itemView.findViewById(R.id.appointment_small_get_direction);


            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (callListener != null && position != RecyclerView.NO_POSITION) {
                        callListener.onItemClick(getItem(position));
                    }
                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (cancelListener != null && position != RecyclerView.NO_POSITION) {
                        cancelListener.onItemClick(getItem(position));
                    }
                }
            });

            direction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (directionListener != null && position != RecyclerView.NO_POSITION) {
                        directionListener.onItemClick(getItem(position));
                    }
                }
            });

        }
    }
}