# ScenarioUtils

ScenarioUtils 是为spring boot bean对象做输出过滤编写的一个简单工具集。该工具支持让你编写的服务不再需要根据不同需求编写新的Bean。少编写点些胶水代码身心才健康。

使用方法：
```Java
public class Project extends ScenarioModel implements Serializable {
    private Integer id;

    private String name;
    
    static public final  String LIST_SCENARIO = "LIST_SCENARIO";
    static public final  String DETAIL_SCENARIO = "DETAIL_SCENARIO";
    @Override
    public HashMap<String, List<String>> scenarios(){
      HashMap<String, List<String>> scenarios  = super.scenarios();
      List<String> fields = new ArrayList<String>();
      String[] attrs =  {"id"};
      Collections.addAll(fields, attrs);
      scenarios.put(Project.LIST_SCENARIO, fields);

      fields = new ArrayList<String>();
      String[] attrs1 = {"id", "name"};
      Collections.addAll(fields, attrs1);
      scenarios.put(Project.DETAIL_SCENARIO, fields);

      return scenarios;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
      public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    
}
```

使用场景：
```Java
@RestController
class ProjectController {
  @RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody RestResponse<Project> list(){
    Project project = new Project();
    project.id = 1;
    project.name = "hello";
    project.setScenario(Project.LIST_SCENARIO);
    return new RestResponse<Project>(project, 1, "ok");
  }
  
  @RequestMapping(value = "/detail", method = RequestMethod.GET)
	public @ResponseBody RestResponse<Project> list(){
    Project project = new Project();
    project.id = 1;
    project.name = "hello";
    project.setScenario(Project.DETAIL_SCENARIO);
    return new RestResponse<Project>(project, 1, "ok");
  }
}
```


输出结果：

/list 输出为：
{code:1,message:"ok",data:{id:1} }

/detail 输出为：
{code:1,message:"ok",data:{id:1,name:"hello"} }


赞赏支持请点击地址：http://www.jianshu.com/p/5c6615a9b244
