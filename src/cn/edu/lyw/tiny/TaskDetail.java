
package cn.edu.lyw.tiny;

import java.util.Map;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.edu.lyw.tiny.util.MissionUtil;

/**
 * 任务的详细信息以及处理信息
 */
public class TaskDetail extends Activity implements OnClickListener{
	
	private Button bt_taskDetail_back;
	private int taskId;
	private Map<String,Object> missionInfo ;
	private Map<String,Object> handleInfo ;
	private static final String KEY_MISSION = "missionInfo";
	private static final String KEY_HANDLE = "missionInfo";
	private static final String DEFAULT_TEXT = "";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_detail);
		
		//得到各个组件
		bt_taskDetail_back = (Button) findViewById(R.id.bt_taskDetail_back);
		findViewById(R.id.rlTaskDeLoading).setVisibility(View.VISIBLE);
		initInfos();
	}

	/**
	 * 
	 */
	private void initInfos() {
		taskId = getIntent().getIntExtra("taskId", 0);
		
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llo_task_detail);
		//得到系统layout资源文件,动态加载layout
		LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				Map<String,Map<String,Object>> infos = MissionUtil.getMission(taskId);
				missionInfo = infos.get(KEY_MISSION);
				handleInfo = infos.get(KEY_HANDLE);
				return null;
			}
			protected void onPostExecute(Void result) {
				findViewById(R.id.rlTaskDeLoading).setVisibility(View.GONE);
				setText(R.id.de_task_title,missionInfo.get("title"));
//				setText(R.id.de_task)
				if(handleInfo != null && handleInfo.size()>0){
					
				}
			};
		}.execute();
		View layout = inflater.inflate(R.layout.handle_info_detail, null);
		linearLayout.addView(layout);
		bt_taskDetail_back.setOnClickListener(TaskDetail.this);
		
	}

	/**
	 * @param deTaskTitle
	 * @param object
	 */
	private void setText(int textViewId, Object object) {
		TextView textView = ((TextView)findViewById(textViewId));
		if(object != null){
			textView.setText(object.toString());
		}else{
			textView.setText(DEFAULT_TEXT);
		}
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_taskDetail_back:
			/*Intent intent = new Intent(this, MainWeibo.class);
			startActivity(intent);*/
			finish();
			break;
		}
	}
}	
