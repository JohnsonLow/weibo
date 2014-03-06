package cn.edu.lyw.tiny;
/*
package cn.edu.csu.iteliter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

*//**
 * CheWenlinag
 * 2014-02-21
 *//*
public class TaskList extends Activity {
	private ListView datalist;
	private SimpleAdapter simpleAdapter;
	private List<Map<String, String>> listmap = new ArrayList<Map<String,String>>();
	private String data[][] = new String[][] { 
			{ "取得两局胜利", "在群雄10V10中取得两局胜利" ,"2014-02-21 20:08","","车文亮","未完成"},
			{ "获得3个名次", "在群雄10V10获得3个名次" ,"2014-02-21 20:08","","求油油","未完成"},
			{ "群雄3局胜利", "在群雄10V10中取得群雄3局胜利" ,"2014-02-21 20:08","","岩岩","未完成"},
			{ "本周得7名将", "在群雄10V10中本周得7名将" ,"2014-02-21 20:08","","天天","未完成"},
			{ "得3个破敌", "在群雄10V10中得3个破敌" ,"2014-02-21 20:08","","炮神","未完成"},
			{ "得到富豪称号", "在群雄10V10中得到富豪称号" ,"2014-02-21 20:08","","付老师","未完成"},
			{ "本月完成70名将", "在群雄10V10中本月完成70名将" ,"2014-02-21 20:08","","湿兄","未完成"}};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_list);
		
		datalist = (ListView) findViewById(R.id.datalist);
		for(int i=0;i<data.length;i++){
			Map<String,String> map = new HashMap<String, String>();
			map.put("task_title", data[i][0]);
			map.put("task_description", data[i][1]);
			map.put("assignTime", data[i][2]);
			map.put("endTime", data[i][3]);
			map.put("assigner", data[i][4]);
			map.put("isComplete", data[i][5]);
			map.put("tv_score", data[i][0]);
			listmap.add(map);
		}
		simpleAdapter = new SimpleAdapter(this, listmap, R.layout.task_list_data,
				new String[]{"task_title","assignTime","asssigner"}, 
				new int[]{R.id.task_title,R.id.assignTime,R.id.assigner});
		datalist.setAdapter(simpleAdapter);
		datalist.setOnItemClickListener(new onItemClickListenerImpl());
		
	}
	private class onItemClickListenerImpl implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position,
				long id) {
			
		}
	}
}
*/