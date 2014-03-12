
package cn.edu.lyw.tiny;

import java.util.ArrayList;
import java.util.List;
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
import cn.edu.lyw.tiny.util.JSONUtils;
import cn.edu.lyw.tiny.util.MissionUtil;

/**
 * 任务的详细信息以及处理信息
 */
public class TaskDetail extends Activity implements OnClickListener{
	
	private Button bt_taskDetail_back;
	private int taskId;
	private Map<String,Object> missionInfo ;
	private List<Map<String,Object>> handleInfo ;
	private static final String KEY_MISSION = "missionInfo";
	private static final String KEY_HANDLE = "handleInfo";
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
	private void showHandleInfo(List<Map<String, Object>> handleInfo){
		if(handleInfo != null){
			findViewById(R.id.no_handle_layout).setVisibility(View.GONE);
			LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llo_task_detail);
			//得到系统layout资源文件,动态加载layout
			LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
			for(int i=0,len=handleInfo.size();i<len;i++){
				View layout = inflater.inflate(R.layout.handle_info_detail, null);
				Map<String,Object> map = handleInfo.get(i);
				((TextView)layout.findViewById(R.id.de_handle_handleTime)).setText(
						map.get("handleTime").toString());
				((TextView)layout.findViewById(R.id.de_handle_content)).setText(
						map.get("content").toString());
				((TextView)layout.findViewById(R.id.de_handle_addTime)).setText(
						map.get("addTime").toString());
				((TextView)layout.findViewById(R.id.de_handle_toName)).setText(
						map.get("toName").toString());
				((TextView)layout.findViewById(R.id.de_handle_toDep)).setText(
						map.get("toDep").toString());
				((TextView)layout.findViewById(R.id.de_handle_handlerName)).setText(
						map.get("handlerName").toString());
				((TextView)layout.findViewById(R.id.de_handle_handlerDep)).setText(
						map.get("handlerDep").toString());
				((TextView)layout.findViewById(R.id.de_handle_status)).setText(
						MissionUtil.getHandlerType(map.get("handleType")));
				linearLayout.addView(layout);
			}
		}else{
			findViewById(R.id.no_handle_layout).setVisibility(View.VISIBLE);
			
		}
	}

	/**
	 * 
	 */
	private void initInfos() {
		taskId = getIntent().getIntExtra("taskId", 0);
		
		new AsyncTask<Void, Void, Void>() {

			@SuppressWarnings("unchecked")
			@Override
			protected Void doInBackground(Void... params) {
				Map<String,Object> infos = MissionUtil.getMission(taskId);
				if(infos != null){
					missionInfo = (Map<String, Object>) infos.get(KEY_MISSION);
					if(infos.get(KEY_HANDLE) != null){
						handleInfo = JSONUtils.getObject(infos.get(KEY_HANDLE).toString(),List.class);
					}
				}
				return null;
			}
			protected void onPostExecute(Void result) {
				findViewById(R.id.rlTaskDeLoading).setVisibility(View.GONE);
				setText(R.id.de_task_title,missionInfo.get("title"));
				setText(R.id.de_task_notes,missionInfo.get("content"));
				setText(R.id.de_task_createTime,missionInfo.get("createTime"));
				setText(R.id.de_task_type,missionInfo.get("type"));
				setText(R.id.de_task_commitTime,missionInfo.get("de_task_commitTime"));
				setText(R.id.de_task_sponsorName,missionInfo.get("sponsorName"));
				setText(R.id.de_task_sponsorDep,missionInfo.get("sponsorDep"));
				setText(R.id.de_task_hanName,missionInfo.get("handlerName"));
				setText(R.id.de_task_handlerDep,missionInfo.get("handlerDep"));
				setText(R.id.de_task_status,MissionUtil.getStatus(missionInfo.get("status")));
				showHandleInfo(handleInfo);
			}
		}.execute();
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
