package com.mcommerce.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mcommerce.nhom8.R;

public class PolicyFragement extends Fragment {
    private View view;
    TextView txtDoiTra, txtShip1,txtShip2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_policy,container,false);
        linkViews();
        loadData();
        return view;
    }

    private void linkViews() {
        txtDoiTra=view.findViewById(R.id.txtDoiTra);
        txtShip1=view.findViewById(R.id.txtShip1);
        txtShip2=view.findViewById(R.id.txtShip2);
    }

    private void loadData() {
        txtDoiTra.setText(
                " Trứng xin phép chỉ xử lý khiếu nại về mọi vấn đề hàng hoá và hoá đơn trong vòng 7 ngày kể từ ngày mua.Chính sách đổi trả hàng chỉ áp dụng cho những mặt hàng là dụng cụ.\n"
                +
                "Nếu vì bất kỳ lý do gì mà quý khách chưa vừa ý hay không còn nhu cầu sử dụng với sản phẩm đã mua, quý khách có thể đổi sang sản phẩm khác có giá trị bằng hoặc lớn hơn trong vòng 07 ngày kể từ ngày mua (Phí đổi trả do khách hàng chịu trách nhiệm). Hàng được đổi trả ngang giá hoặc giá trị cao hơn giá trị hàng đổi trả.\n"
                +
                "Quy định đối với sản phẩm đổi trả\n"
                +
                        "Sản phẩm còn mới 100%, chưa được sử dụng\n" +
                        "Sản phẩm còn đầy đủ bao bì\n" +
                        "Việc đổi sản phẩm quý khách vui lòng thực hiện trực tiếp tại các cửa hàng của Kupkace shop\n" +
                        "Thời gian Đổi là 07 ngày, tính từ ngày mua hàng."
        );
        txtShip1.setText("Phạm vi: Toàn quốc\n" +
                            "Phí vận chuyển:\n"+
                "Miễn phí vận chuyển với đơn hàng từ 500,000đ trở lên\n" +
                "30,000đ với đơn hàng có giá trị thấp hơn 700,000đ\n"+
                "Thời gian vận chuyển:\n"+
                "Nội thành tp. HCM: 1-2 ngày\n" +
                "Miền Nam: 2-3 ngày\n" +
                "Miền Trung: 3-5 ngày\n" +
                "Miền Bắc : 5-7 ngày\n"
        );
        txtShip2.setText("Phạm vi: Nội thành thành phố Hồ Chí Minh\n" +
                "Phí vận chuyển: 30,000đ\n" +
                "Thời gian vận chuyển: 4 tiếng kể từ thời gian nhận được điện thoại xác nhận đơn hàng\n"+
                "Phạm vi: Các tỉnh, thành phố khác\n" +
                "Phí vận chuyển: 50,000đ\n" +
                "Thời gian vận chuyển: 3-5 ngày"
        );

    //Bundle bundle= getIntent().getBundleExtra("myBundle");
        //        String cs=bundle.getInt("numb");
        //        double marks=bundle.getDouble("marks");
        //        boolean checked = bundle.getBoolean("checked");
        //        String say=bundle.getString("say");
        //        txtContent.append("Numb:"+numb+"\n");
    }
}