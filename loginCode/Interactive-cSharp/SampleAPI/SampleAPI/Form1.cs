using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Net;
using Microsoft.Web.WebView2.Core;

namespace SampleAPI
{

    public partial class Form1 : Form
    {

        private static readonly string KeepAliveURL = @"https://identitysso.betfair.com/api/keepAlive";
        private static string LogonURL = @"https://identitysso.betfair.com/view/login?product=[APPKEY]&url=https://www.betfair.com";
        private static string LogoutURL = @"https://identitysso.betfair.com/api/logout?product=[APPKEY]&url=https://www.betfair.com";

        private bool m_LoggedOut = true;
        private string m_SSOID;
        private System.Timers.Timer m_KeepAliveTimer;
        private int m_KeepAliveCount = 0;

        public Form1()
        {
            InitializeComponent();

            m_KeepAliveTimer = new System.Timers.Timer();


            ToolTip tt = new ToolTip();
            tt.IsBalloon = true;
            tt.InitialDelay = 0;
            tt.ShowAlways = true;
            tt.SetToolTip(this.grpAppKey, "Please enter your developer Appkey");
        }

        private async void webview2_NavigationCompleted(object sender, Microsoft.Web.WebView2.Core.CoreWebView2NavigationCompletedEventArgs e)
        {
            string c = "";
            if (webview2.Source.IdnHost == "www.betfair.com")
            {
                List<CoreWebView2Cookie> cookies = await this.webview2.CoreWebView2.CookieManager.GetCookiesAsync(KeepAliveURL);

                foreach (var cookie in cookies)
                {
                    c += cookie.Name + "=" + cookie.Value + ";";
                }
            }

            if (c != "")
                this.ParseCookie(c);

            //If successfull login start KeepAlive 
            if (!m_LoggedOut && !m_KeepAliveTimer.Enabled)
            {
                SetMessage("Logged In");

                this.StartKeepAlive();
            }
        }

        private void ParseCookie(string p_Cookie)
        {
            //If logon on OK get SSOID
            if (m_LoggedOut && p_Cookie.IndexOf("loggedIn=true;") >= 0)
            {

                int SSOIDIndex = p_Cookie.IndexOf("ssoid=");

                if (SSOIDIndex >= 0)
                {
                    m_SSOID = p_Cookie.Substring(SSOIDIndex + "ssoid=".Length);
                    m_SSOID = m_SSOID.Substring(0, m_SSOID.IndexOf(";"));
                    m_LoggedOut = false;
                }
                else
                    m_SSOID = "SSOID Not present";
            }
        }

        private void btnLogout_Click(object sender, EventArgs e)
        {
            m_KeepAliveTimer.Enabled = false;
            m_KeepAliveTimer.Close();
            m_LoggedOut = true;

            System.Uri u = new Uri(LogoutURL);
            webview2.Source = u;
            webview2.NavigateToString(LogoutURL);

            SetMessage("Logged out");
        }

        private void StartKeepAlive()
        {
            m_KeepAliveTimer.Elapsed += new System.Timers.ElapsedEventHandler(OnKeepAliveTimer);
            // Set the Interval to 15 mins.
            m_KeepAliveTimer.Interval = 1000 * 60 * 15;
            m_KeepAliveTimer.Enabled = true;
        }

        private void OnKeepAliveTimer(object source, System.Timers.ElapsedEventArgs e)
        {
            System.Uri u = new Uri(KeepAliveURL);
            this.Invoke((MethodInvoker)delegate {
                webview2.Source = u;
                webview2.NavigateToString(KeepAliveURL);
            });

            m_KeepAliveCount++;
            SetMessage("Keep Alive sent " + m_KeepAliveCount);
        }

        //Thread safe TextBox Set text
        delegate void SetTextCallback(string p_Text);
        private void SetMessage(string p_Text)
        {
            // InvokeRequired required compares the thread ID of the
            // calling thread to the thread ID of the creating thread.
            // If these threads are different, it returns true.
            if (this.txtMessage.InvokeRequired)
            {
                SetTextCallback d = new SetTextCallback(SetMessage);
                this.Invoke(d, new object[] { p_Text });
            }
            else
            {
                this.txtMessage.Text = p_Text;
            }
        }

        private void btnLogon_Click(object sender, EventArgs e)
        {
            //Put Appkey in Logon anf Logout URLs
            LogonURL = LogonURL.Replace("[APPKEY]", this.txtAppKey.Text);
            LogoutURL = LogoutURL.Replace("[APPKEY]", this.txtAppKey.Text);

            //Give embeded web browser Betfair api login page URL
            System.Uri u = new Uri(LogonURL);

            this.webview2.Source = u;

            this.btnLogout.Enabled = true;
        }

        private void txtAppKey_KeyUp(object sender, KeyEventArgs e)
        {
            this.btnLogon.Enabled = true;
        }

        private void webview2_Click(object sender, EventArgs e)
        {

        }
    }
}
