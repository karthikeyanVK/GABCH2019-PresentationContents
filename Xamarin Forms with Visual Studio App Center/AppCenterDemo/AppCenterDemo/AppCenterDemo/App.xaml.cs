using Microsoft.AppCenter.Analytics;
using Microsoft.AppCenter.Crashes;
using System;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

[assembly: XamlCompilation(XamlCompilationOptions.Compile)]
namespace AppCenterDemo
{
    public partial class App : Application
    {
        public string LogTag { get; private set; }

        public App()
        {
            InitializeComponent();

            MainPage = new MainPage();
        }

        protected override void OnStart()
        {
            // Handle when your app starts


            Crashes.SetEnabledAsync(true);
            Microsoft.AppCenter.AppCenter.Start("ios={Your iOS App secret here};" +
               "uwp={Your UWP App secret here};" +
               "android=1365703f-f659-48fb-acfe-38e00b07e0a3;",
               typeof(Analytics), typeof(Crashes));

            Crashes.ShouldAwaitUserConfirmation = ConfirmationHandler;
        }

        protected override void OnSleep()
        {
            // Handle when your app sleeps
        }

        protected override void OnResume()
        {
            // Handle when your app resumes
        }

        private bool ConfirmationHandler()
        {
            Xamarin.Forms.Device.BeginInvokeOnMainThread(() =>
            {
                Current.MainPage.DisplayActionSheet("Crash detected. Send anonymous crash report?", null, null, "Send", "Always Send", "Don't Send").ContinueWith((arg) =>
                {
                    var answer = arg.Result;
                    UserConfirmation userConfirmationSelection;
                    if (answer == "Send")
                    {
                        userConfirmationSelection = UserConfirmation.Send;
                    }
                    else if (answer == "Always Send")
                    {
                        userConfirmationSelection = UserConfirmation.AlwaysSend;
                    }
                    else
                    {
                        userConfirmationSelection = UserConfirmation.DontSend;
                    }
                    Microsoft.AppCenter.AppCenterLog.Debug(LogTag, "User selected confirmation option: \"" + answer + "\"");
                    Crashes.NotifyUserConfirmation(userConfirmationSelection);
                });
            });

            return true;
        }
    }
}
