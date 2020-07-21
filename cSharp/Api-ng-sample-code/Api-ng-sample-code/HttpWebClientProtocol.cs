using System;
using System.Collections.Generic;
using System.Net;
using System.Text;

namespace Api_ng_sample_code
{
    /// <summary>
    /// Replacement for System.Web.Services.HttpWebClientProtocol
    /// </summary>
    public abstract class HttpWebClientProtocol
    {
        protected WebResponse GetWebResponse(WebRequest request)
        {
            return request.GetResponse();
        }
    }
}
