$(function () {
	// body...
	// 
	// 
    var myChart = echarts.init(document.getElementById('main'));

	var char;
    $.ajax({
        type:'get',
        url:'jsp/statistics/month.action',
        dataType:"json",
        success : function (data) {
        	alert(data);
        	 myChart.setOption({
        	        color: ['#3398DB'],
        	        tooltip : {
        	            trigger: 'axis',
        	            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
        	                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        	            }
        	        },
        	        grid: {
        	            left: '3%',
        	            right: '4%',
        	            bottom: '3%',
        	            containLabel: true
        	        },
        	        xAxis : [
        	            {
        	                type : 'category',
        	                data : ['一月', '二月', '三月', '四月', '五月', '六月', '七月','八月', '九月', '十月', '十一月','十二月'],
        	                axisTick: {
        	                    alignWithLabel: true
        	                }
        	            }
        	        ],
        	        yAxis : [
        	            {
        	                type : 'value'
        	            }
        	        ],
        	        series : [
        	            {
        	                name:'直接访问',
        	                type:'bar',
        	                barWidth: '60%',
        	                data:data
        	            }
        	        ]
        	    });
        }
    }); 
   

})