namespace SampleAPI
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.btnLogout = new System.Windows.Forms.Button();
            this.txtMessage = new System.Windows.Forms.TextBox();
            this.grpAppKey = new System.Windows.Forms.GroupBox();
            this.txtAppKey = new System.Windows.Forms.TextBox();
            this.btnLogon = new System.Windows.Forms.Button();
            this.webview2 = new Microsoft.Web.WebView2.WinForms.WebView2();
            this.grpAppKey.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.webview2)).BeginInit();
            this.SuspendLayout();
            // 
            // btnLogout
            // 
            this.btnLogout.Enabled = false;
            this.btnLogout.Location = new System.Drawing.Point(878, 573);
            this.btnLogout.Name = "btnLogout";
            this.btnLogout.Size = new System.Drawing.Size(135, 23);
            this.btnLogout.TabIndex = 2;
            this.btnLogout.Text = "Logout";
            this.btnLogout.UseVisualStyleBackColor = true;
            this.btnLogout.Click += new System.EventHandler(this.btnLogout_Click);
            // 
            // txtMessage
            // 
            this.txtMessage.Font = new System.Drawing.Font("Arial Narrow", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtMessage.Location = new System.Drawing.Point(195, 239);
            this.txtMessage.Multiline = true;
            this.txtMessage.Name = "txtMessage";
            this.txtMessage.Size = new System.Drawing.Size(637, 60);
            this.txtMessage.TabIndex = 3;
            // 
            // grpAppKey
            // 
            this.grpAppKey.Controls.Add(this.txtAppKey);
            this.grpAppKey.Location = new System.Drawing.Point(27, 551);
            this.grpAppKey.Name = "grpAppKey";
            this.grpAppKey.Size = new System.Drawing.Size(281, 64);
            this.grpAppKey.TabIndex = 4;
            this.grpAppKey.TabStop = false;
            this.grpAppKey.Text = "Application Key";
            // 
            // txtAppKey
            // 
            this.txtAppKey.Location = new System.Drawing.Point(33, 25);
            this.txtAppKey.Name = "txtAppKey";
            this.txtAppKey.Size = new System.Drawing.Size(218, 20);
            this.txtAppKey.TabIndex = 0;
            this.txtAppKey.KeyUp += new System.Windows.Forms.KeyEventHandler(this.txtAppKey_KeyUp);
            // 
            // btnLogon
            // 
            this.btnLogon.Enabled = false;
            this.btnLogon.Location = new System.Drawing.Point(328, 576);
            this.btnLogon.Name = "btnLogon";
            this.btnLogon.Size = new System.Drawing.Size(135, 23);
            this.btnLogon.TabIndex = 5;
            this.btnLogon.Text = "Show Logon Page";
            this.btnLogon.UseVisualStyleBackColor = true;
            this.btnLogon.Click += new System.EventHandler(this.btnLogon_Click);
            // 
            // webview2
            // 
            this.webview2.AllowExternalDrop = true;
            this.webview2.CreationProperties = null;
            this.webview2.DefaultBackgroundColor = System.Drawing.Color.White;
            this.webview2.Location = new System.Drawing.Point(27, 21);
            this.webview2.Name = "webview2";
            this.webview2.Size = new System.Drawing.Size(986, 524);
            this.webview2.TabIndex = 6;
            this.webview2.ZoomFactor = 1D;
            this.webview2.NavigationCompleted += new System.EventHandler<Microsoft.Web.WebView2.Core.CoreWebView2NavigationCompletedEventArgs>(this.webview2_NavigationCompleted);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.ActiveBorder;
            this.ClientSize = new System.Drawing.Size(1040, 639);
            this.Controls.Add(this.webview2);
            this.Controls.Add(this.btnLogon);
            this.Controls.Add(this.grpAppKey);
            this.Controls.Add(this.btnLogout);
            this.Controls.Add(this.txtMessage);
            this.Name = "Form1";
            this.Text = "Form1";
            this.grpAppKey.ResumeLayout(false);
            this.grpAppKey.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.webview2)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.Button btnLogout;
        private System.Windows.Forms.TextBox txtMessage;
        private System.Windows.Forms.GroupBox grpAppKey;
        private System.Windows.Forms.TextBox txtAppKey;
        private System.Windows.Forms.Button btnLogon;
        private Microsoft.Web.WebView2.WinForms.WebView2 webview2;
    }
}

