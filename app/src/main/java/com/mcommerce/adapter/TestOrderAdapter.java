package com.mcommerce.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcommerce.model.OrderModel;
import com.mcommerce.nhom8.R;

import java.util.ArrayList;
import java.util.List;

public class TestOrderAdapter extends RecyclerView.Adapter<TestOrderAdapter.ViewHolder> implements Filterable
{

    private List<OrderModel> orderModelList;
    private List<OrderModel> orderModelListOld;
    private Activity context;

    public TestOrderAdapter(List<OrderModel> orderModelList, Activity context) {
        this.orderModelList = orderModelList;
        this.orderModelListOld = orderModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_item_coming_order,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtPrice.setText(orderModelList.get(position).getOrderPrice());
        holder.txtDes.setText(orderModelList.get(position).getOrderDes());
        holder.txtDate.setText(orderModelList.get(position).getOrderDate());
        holder.txtAdd.setText(orderModelList.get(position).getOrderAddress());
        holder.imv.setImageResource(orderModelList.get(position).getOrderImage());

    }

    @Override
    public int getItemCount() {
        return orderModelList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                //Tạo biến strSearch lưu dữ liệu user nhập vào thanh search
                String strSearch = charSequence.toString();

                //Nếu user không tìm kiếm gì cả => biến strSearch sẽ empty
                if (strSearch.isEmpty()){

                    //orderModelList chứa toàn bộ dữ liệu item đã tạo
                     /*biến orderModelListOld được tạo ở dòng 26, ta sẽ cho nó lấy mọi giá trị mà
                    user nhập vào (dòng 31)*/
                    orderModelList = orderModelListOld;
                }

                // trường hợp user tìm kiếm gì đó
                else {
                    //tạo một list item mới để chứa những kết quả phù hợp với những gì người
                    //dùng nhập
                    List<OrderModel> list = new ArrayList<>();

                    //chạy dòng for duyệt qua từng item trong list item
                    for (OrderModel orderModel: orderModelList){

                        //ở đây thì cho mặc định search theo địa chỉ, ở màn hình khác cho search theo
                        //id hay tên thì thay đổi giá trị đoạn "getOrderAddress" thành get cái mình muốn

                        // nếu item trong list được lấy ra có kết quả giống với biến strSearch thì
                        // thêm item đó vào list (đã tạo ở dòng 82)
                        if (orderModel.getOrderAddress().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(orderModel);
                        }
                    }

                    // sau khi chạy xong dòng for : có nghĩa ta đã tìm hết các sản phẩm trùng khớp với
                    //cái khách hàng tìm kiếm và lưu vào biến list.
                    // lúc này cho orderModelList lấy toàn bộ dữ liệu của list item
                    orderModelList = list;
                }


                // lúc này orderModelList đã chứa những dữ liệu phù hợp với cả 2 trường hợp user tìm kiếm hoặc
                // user ko tìm kiếm.

                // ta tạo 1 biến filterResult để hàm Filter trả kết quả cho màn hình
                FilterResults filterResults = new FilterResults();

                //gán orderModelList thành giá trị cho filterResult.
                filterResults.values = orderModelList;

                // trả giá trị về
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                orderModelList = (List<OrderModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imv;
        TextView txtPrice, txtDes, txtDate, txtAdd;
        Button btnXemChiTiet, btnTheoDoiDon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imv = itemView.findViewById(R.id.imv_lyComingOrder);
            txtAdd = itemView.findViewById(R.id.txtAddress_lyComingOrder);
            txtDes = itemView.findViewById(R.id.txtDes_lyComingOrder);
            txtPrice = itemView.findViewById(R.id.txtPrice_lyComingOrder);
            txtDate = itemView.findViewById(R.id.txtDate_lyComingOrder);
            btnTheoDoiDon = itemView.findViewById(R.id.btnTheoDoiDon_lyComingOrder);
            btnXemChiTiet = itemView.findViewById(R.id.btnXemChiTiet_lyComingOrder);
        }
    }
}
