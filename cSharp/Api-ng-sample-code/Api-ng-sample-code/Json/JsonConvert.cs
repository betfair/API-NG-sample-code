using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using Newtonsoft.Json;
using System.IO.Compression;


namespace Api_ng_sample_code.Json
{
    class JsonConvert
    {

        public static JsonResponse<T> Import<T>(TextReader reader)
        {
            var jsonResponse = reader.ReadToEnd();
            return Deserialize<JsonResponse<T>>(jsonResponse);
        }
        public static JsonResponse<T> ImportGzip<T>(Stream reader)
        {
            var jsonResponse = decompress(reader);
            return Deserialize<JsonResponse<T>>(jsonResponse);
        }

        public static string decompress(Stream reader)
        {
            using (GZipStream stream = new GZipStream(reader, CompressionMode.Decompress))
            {
                const int size = 4096;
                byte[] buffer = new byte[size];
                using (MemoryStream memory = new MemoryStream())
                {
                    int count = 0;
                    do
                    {
                        count = stream.Read(buffer, 0, size);
                        if (count > 0)
                        {
                            memory.Write(buffer, 0, count);
                        }
                    }
                    while (count > 0);
                    return System.Text.ASCIIEncoding.ASCII.GetString(memory.ToArray());
                }

            }
        }
        public static T Deserialize<T>(string json)
        {
            return Newtonsoft.Json.JsonConvert.DeserializeObject<T>(json);
        }

        //Used for json rpc calls to create a body
        public static void Export(JsonRequest request, TextWriter writer)
		{
			var json = Serialize<JsonRequest>(request);
			writer.Write(json);
		}

        public static string Serialize<T>(T value)
		{

			var settings = new JsonSerializerSettings { NullValueHandling = NullValueHandling.Ignore};

			return Newtonsoft.Json.JsonConvert.SerializeObject(value, settings);
		}
    }
}
