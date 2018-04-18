import Component from '@ember/component';
import defaultTheme from '../themes/default-theme';


export default Component.extend({





  chartOptions: {
    chart: {
      type: 'bar'
    },
    title: {
      text: 'Course vs SelectedSemester + RowAvg'
    },
    xAxis: {
      categories: ['COMX 004', 'COMX 021', 'COMX 411']
    },
    yAxis: {
      allowDecimals: true,
      title: {
        text: 'Score'
      }
    }
  },

  chartData: [
    {
      name: 'Fall 2014',
      data: [5, 100, 20]
    },
    {
      name: 'Row Average',
      data: [25, 30, 15]
    }
  ],

  theme: defaultTheme

























}); //end Component.extent
