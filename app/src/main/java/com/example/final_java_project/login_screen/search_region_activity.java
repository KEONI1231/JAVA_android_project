package com.example.final_java_project.login_screen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_java_project.R;
import com.example.final_java_project.list_adapter.CustomSearchListView;

import java.util.ArrayList;

public class search_region_activity extends AppCompatActivity {
    ArrayList<CustomSearchListView.ListData> listViewData = new ArrayList<>();
    int fillterIndex = 0;
    int fillterSuccCount = 0;

    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_region_listview);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.first_screen_appbar);
        ListView listView;
        listView = findViewById(R.id.listview);
        String[] body_1 = {"가나", "가봉", "가이아나", "감비아", "과테말라", "그레나다 / 그레네이더", "그루지야", "그리스", "기니", "기니비사우 / 기네비싸우", "나미비아", "나우루", "나이지리아", "남수단", "남아프리카 공화국", "네덜란드 / 네데를란드", "네팔",
                "노르웨이", "뉴질랜드", "니제르", "니카라과", "덴마크", "도미니카 연방", "도미니카 공화국", "독일", "동티모르", "라오스", "라이베리아", "라트비아", "러시아", "레바논", "레소토", "루마니아", "룩셈부르크", "르완다",
                "리비아", "리투아니아", "리히텐슈타인", "마다가스카르", "마셜 제도", "말라위", "말레이시아", "말리", "멕시코", "모나코", "모로코", "모리셔스", "모리타니", "모잠비크", "몬테네그로", "몰도바", "몰디브",
                "몰타", "몽골", "미국", "미얀마", "미크로네시아 연방", "바누아투", "바레인", "바베이도스", "바티칸", "바하마", "방글라데시", "베네수엘라", "베냉", "베트남", "벨기에", "벨라루스", "벨리즈", "보스니아 헤르체고비나", "보츠와나", "볼리비아",
                "부룬디", "부르키나파소", "부탄", "북마케도니아", "북조선(북한)", "불가리아", "브라질", "브루나이", "산마리노", "사모아", "사우디아라비아", "상투메 프린시페", "세네갈", "세르비아", "세이셸",
                "세인트루시아", "세인트빈센트 그레나딘", "세인트키츠 네비스", "소말리아", "솔로몬 제도", "수단", "수리남", "스리랑카", "스웨덴", "스위스", "스페인(에스파냐)", "슬로바키아", "슬로베니아", "시리아", "시에라리온",
                "싱가포르", "아랍에미리트", "아르메니아", "아르헨티나", "아이슬란드", "아이티", "아일랜드", "아제르바이잔", "아프가니스탄", "안도라", "알바니아", "알제리", "앙골라", "앤티가 바부다", "에스와티니", "에콰도르", "에리트레아", "에스토니아", "에티오피아",
                "엘살바도르", "영국", "예멘", "오만", "오스트레일리아", "오스트리아", "온두라스", "요르단", "우간다", "우루과이", "우즈베키스탄", "우크라이나", "이라크", "이란", "이스라엘", "이집트", "이탈리아",
                "인도", "인도네시아", "일본", "자메이카", "잠비아", "적도 기니", "중앙아프리카공화국", "중화인민공화국 (중국)", "지부티", "짐바브웨", "차드", "체코", "칠레", "카메룬", "카자흐스탄", "카보베르데", "카타르", "캄보디아",
                "캐나다", "케냐", "코모로", "코스타리카", "코트디부아르", "쿠바", "쿠웨이트", "콜롬비아", "콩고인민공화국", "콩고민주공화국", "크로아티아", "키르기스스탄", "키리바시", "키프로스", "타지키스탄", "탄자니아",
                "태국", "토고", "통가", "투르크메니스탄", "투발루", "튀니지", "튀르키예", "트리니다드 토바고", "파나마", "파라과이", "파키스탄", "파푸아뉴기니", "팔라우", "페루", "포르투갈", "폴란드", "프랑스",
                "피지", "핀란드", "필리핀", "한국", "헝가리"};
        String[] fillterRegion = new String[body_1.length];
        /*for(int i =0 ; i < fillterRegion.length; i++) {
            fillterRegion[i] = body_1[i];
        }
        for(int i = 0; i < fillterRegion.length; i++) {
            System.out.println(i + " : " + fillterRegion[i]);
        }*/
        //System.out.println(fillterRegion.length);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.search_custom_listview, null);

        EditText editSearch = (EditText) findViewById(R.id.search_region_editText);

        for (int i = 0; i < body_1.length; i++) {
            CustomSearchListView.ListData listData = new CustomSearchListView.ListData();
            listData.body_1 = body_1[i];
            listViewData.add(listData);
        }
        //listViewData.clear();
        ListAdapter oAdapter = new CustomSearchListView(listViewData);
        listView.setAdapter(oAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                String item = listViewData.get(position).body_1;
                intent.putExtra("result", item);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        listView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                listView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                String text = editSearch.getText().toString();
                fillterIndex = 0;
                for (int i = 0; i < fillterRegion.length; i++) {
                    fillterRegion[i] = null;
                }
                //  listViewData.clear();
                for (int i = 0; i < body_1.length; i++) {
                    if (body_1[i].contains(text)) {
                        fillterRegion[fillterIndex] = body_1[i];
                        //System.out.println(text);
                        if (fillterIndex + 1 <= 193) {
                            fillterIndex = fillterIndex + 1;
                        }
                        fillterSuccCount += 1;
                    }
                }
                if (fillterSuccCount == 0) {
                    fillterIndex = 0;
                    listViewData.clear();
                    ListAdapter oAdapter = new CustomSearchListView(listViewData);
                    listView.setAdapter(oAdapter);
                }

                if (fillterRegion[0] != null) {
                    fillterIndex = 0;
                    listViewData.clear();
                    for (int i = 0; i < fillterRegion.length; i++) {
                        if (fillterRegion[i] == null) {
                            break;
                        }
                        CustomSearchListView.ListData listData = new CustomSearchListView.ListData();
                        listData.body_1 = fillterRegion[i];
                        listViewData.add(listData);
                    }
                    ListAdapter oAdapter = new CustomSearchListView(listViewData);
                    listView.setAdapter(oAdapter);

                }
                if (text.length() == 0) {
                    fillterIndex = 0;
                    fillterSuccCount = 0;
                    listViewData.clear();
                    for (int i = 0; i < body_1.length; i++) {
                        CustomSearchListView.ListData listData = new CustomSearchListView.ListData();
                        listData.body_1 = body_1[i];
                        listViewData.add(listData);
                    }

                    ListAdapter oAdapter = new CustomSearchListView(listViewData);
                    listView.setAdapter(oAdapter);

                }

            }
        });
    }

}



