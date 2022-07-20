<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/admin/common.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>销售视图</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="${basePath}/admin/js/echarts.min.js"></script>
    <script src="${basePath}/admin/js/jquery-3.4.1.min.js"></script>
</head>
<body>
	<table>
		<tr>
			<td>
					<div id="chartmain" style="width: 500px; height: 300px;"></div></td>
			<td><div id="main1"
					style="height: 300px; width:800px; padding: 20px"></div></td>
		</tr>
		<tr>
			<td colspan=2>
					<div id="main" style="height: 350px"></div>
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
		var date = []; //日期
		var number = []; //数量
		var names ="销售额"; //名字
		$.ajax({
			type : "get",
			url : "${basePath}/OrderServlet?action=findViewTime", //请求
			dataType : "json", //返回数据形式为json
			success : function(result) {
				//请求成功时执行该函数内容，result即为服务器返回的json对象
				$.each(result, function(index, item) {
					date.push(item.begintime);
					number.push(item.total);
					//names.push("用户");
				});
                
				
				// 基于准备好的dom，初始化echarts实例
				var myChart = echarts.init(document.getElementById('main1'));
				// 指定图表的配置项和数据
				var option = {
					title : {
						text : names[0]
					},
					xAxis : {
						name : '日期',
						type : 'category',
						data : date
					},
					yAxis : {
 
						name : '数量',
						type : 'value',
						data :number
					},
					series : [ {
						data : number,
						type : 'line'
					} ]
				};
				//加载echarts
				myChart.setOption(option);
			}
		})
	</script>
<script type="text/javascript">
    //初始化echarts
    function chushihua(myChart){
        var option = {
            title:{
                text:'图书馆数据统计'
            },            
            series:[{
                name:'访问量',
                type:'pie', 
                radius:'90%', 
                data:[
                    {value:1,name:'无'},
                ]
            }]
        };

        myChart.setOption(option);   
    }

    //从数据库读取数据赋值给echarts
    function fuzhi(myChart){
        $.ajax({
            contentType : "application/json",
            type : "POST",
            url : "${basePath}/OrderServlet?action=findView",
            dataType : "json",
            success : function(data) {
                //创建一个数组，用来装对象传给series.data，因为series.data里面不能直接鞋for循环
                var servicedata=[];

                for(var i=0;i<data.length;i++){
                    var obj=new Object();
                    obj.name=data[i].name; 
                    obj.value=data[i].total;
                    servicedata[i]=obj;
                }

                myChart.setOption({
                    title:{
                    text:'图书借阅统计'
                    },            
                    series:[{
                        name:'借阅量',
                        type:'pie', 
                        radius:'65%', 
                        data:servicedata
                    }]
					
                });

            }
        });
    }

    //初始化echarts实例
    var myChart = echarts.init(document.getElementById('chartmain'));
    chushihua(myChart);
    fuzhi(myChart);    

</script>
	
    <script type="text/javascript">
              var  myChart = echarts.init(document.getElementById('main'));
              var arr1=[],arr2=[];
              function arrTest(){
                $.ajax({
                  type:"post",
                  async:false,
                  url:"${basePath}/OrderServlet?action=findView",
                  data:{},
                  dataType:"json",
                  success:function(result){
                    if (result) {
                      for (var i = 0; i < result.length; i++) {
                          arr1.push(result[i].name);
                          arr2.push(result[i].total);
                      }
                    }
                  }
                })
                return arr1,arr2;
              }
              arrTest();
              var  option = {
                    tooltip: {
                        show: true
                    },
                    legend: {
                       data:['total']
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : arr1
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            "name":"借阅量",
                            "type":"bar",
                            "data":arr2
                        }
                    ]
                };
                // 为echarts对象加载数据
                myChart.setOption(option);
            // }
    </script>
</body>
</html>
