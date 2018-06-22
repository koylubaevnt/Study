package com.pa.kasu.refueller.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pa.kasu.refueller.R;
import com.pa.kasu.refueller.dto.Dispenser;
import com.pa.kasu.refueller.dto.DispenserState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Адаптер по работе с ТРК (обеспечивает взаимодействие с View и передачу данных )
 */
public class DispencerAdapter extends BaseAdapter {

    /**
     * Rjyntrcn
     */
    private Context mContext;

    private LayoutInflater mLayoutInflater;
    /**
     * Список ТРК
     */
    private List<Dispenser> mDispensers;

    public DispencerAdapter(Context context, List<Dispenser> fuellingPoints) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mDispensers = fuellingPoints;
    }

    public List<Dispenser> getDispencers() {
        return mDispensers;
    }

    public void setDispencers(List<Dispenser> fuellingPoints) {
        boolean isHasChanges = false;
        if(fuellingPoints == null) {
            mDispensers.clear();
            isHasChanges = true;
        } else {
            // Вставим отсутствующие ТРК
            for(Dispenser dispenser : fuellingPoints) {
                boolean needInsert = true;
                for(Dispenser dispenserAdapter : mDispensers) {
                    if (dispenser.getId().equals(dispenserAdapter.getId())) {
                        needInsert = false;
                        break;
                    }
                }
                if(needInsert) {
                    isHasChanges = true;
                    mDispensers.add(dispenser);
                }
            }

            // Обновим информацию по ТРК
            List<Dispenser> dispensersForRemove = new ArrayList<>();
            for(Dispenser dispenserAdapterData : mDispensers) {
                Dispenser dispenserForUpdate = null;
                for(Dispenser dispenser : fuellingPoints) {
                    if(dispenser.getId().equals(dispenserAdapterData.getId())) {
                        dispenserForUpdate = dispenser;
                        break;
                    }
                }
                if (dispenserForUpdate != null)
                {
                    if(dispenserForUpdate.isSomeDataChange(dispenserAdapterData)) {
                        isHasChanges = true;
                        dispenserAdapterData.setState(dispenserForUpdate.getState());
                        dispenserAdapterData.setSubState(dispenserForUpdate.getSubState());
                        dispenserAdapterData.setVolumePlan(dispenserForUpdate.getVolumePlan());
                        dispenserAdapterData.setVolumeFact(dispenserForUpdate.getVolumeFact());
                        dispenserAdapterData.setLockId(dispenserForUpdate.getLockId());
                    }
                } else {
                    dispensersForRemove.add(dispenserAdapterData);
                }
            }
            // Удалим отсуствующие ТРК
            for(Dispenser dispenserForRemove : dispensersForRemove) {
                isHasChanges = true;
                mDispensers.remove(dispenserForRemove);
            }

            Collections.sort(mDispensers, new Comparator<Dispenser>() {
                @Override
                public int compare(Dispenser o1, Dispenser o2) {
                    return o1.getNumber().compareTo(o2.getNumber());
                }
            });
        }
        if(isHasChanges) {
            notifyDataSetChanged();
        }
        //this.mDispensers = fuellingPoints;
    }

    @Override
    public int getCount() {
        if(mDispensers != null) {
            return mDispensers.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(mDispensers != null && position >= 0 && position < getCount()) {
            return mDispensers.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if(mDispensers != null && position >= 0 && position < getCount()) {
            return mDispensers.get(position).getId();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1
        final Dispenser dispenser = mDispensers.get(position);

        View cellDispencerGrid = convertView;
        // 2
        if (convertView == null) {
            /* final LayoutInflater layoutInflater = LayoutInflater.from(mContext); */
            cellDispencerGrid = mLayoutInflater.inflate(R.layout.fuel_dispenser_item, parent, false);
        }

        // 3
        final ImageView dispenserCircleImageView = cellDispencerGrid.findViewById(R.id.iv_dispenser_status);
        final TextView dispencerNumberTextView = cellDispencerGrid.findViewById(R.id.tv_dispenser_num);

        //TODO:
        final ProgressBar progressBarIndeterminate = cellDispencerGrid.findViewById(R.id.pb_dispenser_indeterminate);
        final ProgressBar progressBarDeterminate = cellDispencerGrid.findViewById(R.id.pb_dispenser_determinate);

        final ImageView dispenserCheckImageView = cellDispencerGrid.findViewById(R.id.iv_dispenser_is_my);

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            if (progressBarIndeterminate.getIndeterminateDrawable() != null) {
                progressBarIndeterminate.getIndeterminateDrawable().setColorFilter(mContext.getResources().getColor(R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
            }
        }
        int backgroundColor;
        int numberColor = mContext.getResources().getColor(R.color.colorOnWhiteNumber);
        //TODO: считать данные из @code dispenser

        if(!dispenser.getState().equals(DispenserState.FUELLING)) {
            progressBarDeterminate.setVisibility(View.INVISIBLE);
            progressBarIndeterminate.setVisibility(View.INVISIBLE);
        }

        if(dispenser.isMy()) {
            dispenserCheckImageView.setVisibility(View.VISIBLE);
        } else {
            dispenserCheckImageView.setVisibility(View.INVISIBLE);
        }

        if(dispenser.isBusy()) {
            backgroundColor = mContext.getResources().getColor(R.color.colorBusy);
            numberColor = mContext.getResources().getColor(R.color.colorOnGrayNumber);
        } else {
            switch (dispenser.getState()) {
                case IDLE:
                    backgroundColor = mContext.getResources().getColor(R.color.colorFree);
                    break;
                case FUELLING:
                    backgroundColor = mContext.getResources().getColor(R.color.colorFuelling);
                    numberColor = mContext.getResources().getColor(R.color.colorOnGrayNumber);
                    //TODO: в зависимости от типа налива...
                    if (dispenser.getVolumePlan().intValue() < 70) {
                        progressBarDeterminate.setVisibility(View.VISIBLE);
                        progressBarDeterminate.setMax(dispenser.getVolumePlan().intValue());
                        progressBarDeterminate.setProgress(dispenser.getVolumeFact().intValue());
                    } else {
                        progressBarIndeterminate.setVisibility(View.VISIBLE);
                    }
                    break;
                case WAITING_PAYING:
                    backgroundColor = mContext.getResources().getColor(R.color.colorWaitPaying);
                    numberColor = mContext.getResources().getColor(R.color.colorWhite);
                    break;
                case PAYED:
                    backgroundColor = mContext.getResources().getColor(R.color.colorFree);
                    break;
                case CLOSE:
                default:
                    backgroundColor = mContext.getResources().getColor(R.color.colorBackground);
                    numberColor = mContext.getResources().getColor(R.color.colorOnGrayNumber);
                    break;
            }
        }
        // 4
        dispenserCircleImageView.setColorFilter(backgroundColor, PorterDuff.Mode.SRC_IN);
        dispencerNumberTextView.setTextColor(numberColor);
        dispencerNumberTextView.setText(dispenser.getNumber());

        return cellDispencerGrid;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    /*
    public static class RecordHolder {
        public static TextView txtTitle;
        ImageView imageItem;

    }
    */
}
