using Microsoft.AppCenter.Analytics;
using Microsoft.AppCenter.Crashes;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace AppCenterDemo
{
    public partial class MainPage : ContentPage
    {
        string error;
        public MainPage()
        {
            InitializeComponent();
        }

        private void BtnEvent_Clicked(object sender, EventArgs e)
        {
            Analytics.TrackEvent("Clicked Event Button");
        }

        private void BtnCrash_Clicked(object sender, EventArgs e)
        {
            try
            {
                //Crashes.GenerateTestCrash();  --First Test Crash 
                error = "xyz"; 
                var crash = Convert.ToInt32(error);
                
            }
            catch (Exception ex)
            {
                Crashes.TrackError(ex);
            }
        }
    }
}
