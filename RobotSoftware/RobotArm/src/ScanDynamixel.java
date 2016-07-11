

import java.io.IOException;
import java.util.*;


public class ScanDynamixel
{
	public static void main(String args[])
	{
		final int P_MODEL_NUMBER_L = 0;	// Model number low byte
		final int P_MODEL_NUMBER_H = 1;	// Model number high byte
		final int P_VERSION = 2;	// Firmware version

	//	Scanner sc = new Scanner(System.in);

		int res;
		int i, j, n;
		int bValue;
		int wValue;

		res = dynamixel_jni.dxl_initialize();
		if (res == 1)
		{
			System.out.println("Succeed to open USB2Dynamixel!");
			System.out.println("Scan start!(Quit to press ENTER key)");
			for (i = 1; i < 255; i++)
			{
				dynamixel_jni.dxl_set_baud(i);
				System.out.printf("==== Baudrate number: %d =====\n", i);

				for (j = 0, n = 0; j < dynamixel_jni.BROADCAST_ID; j++)
				{
					dynamixel_jni.dxl_ping(j);
					if (dynamixel_jni.dxl_get_result() == dynamixel_jni.COMM_RXSUCCESS)
					{
						n++;
						System.out.printf("-ID:%d (", j);
						wValue = dynamixel_jni.dxl_read_word(j, P_MODEL_NUMBER_L);
						if (dynamixel_jni.dxl_get_result() == dynamixel_jni.COMM_RXSUCCESS)
							System.out.printf("Model number:%d, ", wValue);
						else
						{
							ErrorDisplay();
							System.out.printf(", ");
						}

						bValue = dynamixel_jni.dxl_read_byte(j, P_VERSION);
						if (dynamixel_jni.dxl_get_result() == dynamixel_jni.COMM_RXSUCCESS)
							System.out.printf("Version:%d", bValue);
						else
							ErrorDisplay();

						System.out.println(")");
					}else{
                                            ErrorDisplay();
                                            System.out.println("Can't find...");
                                            break;
                                        }

					// key check
					try
					{
						if (System.in.available() > 0)
						{
							System.out.println("Scan break!");
							dynamixel_jni.dxl_terminate();
							System.out.println("Press any key to terminate...");
							System.exit(0);
						}
					}
					catch (IOException ioe)
					{
						System.out.println("IO error");
					}
				}
				System.out.printf("Total found number: %d\n", n);
			}
		}
		else
			System.out.println("Failed to open USB2Dynamixel!");

		dynamixel_jni.dxl_terminate();
		System.out.println("Press any key to terminate...");
	}

	static void ErrorDisplay()
	{
		switch (dynamixel_jni.dxl_get_result())
		{
			case dynamixel_jni.COMM_TXFAIL:
				System.out.print("COMM_TXFAIL");
				break;

			case dynamixel_jni.COMM_TXERROR:
				System.out.print("COMM_TXERROR");
				break;

			case dynamixel_jni.COMM_RXFAIL:
				System.out.print("COMM_RXFAIL");
				break;

			case dynamixel_jni.COMM_RXWAITING:
				System.out.print("COMM_RXWAITING");
				break;

			case dynamixel_jni.COMM_RXTIMEOUT:
				System.out.print("COMM_RXTIMEOUT");
				break;

			case dynamixel_jni.COMM_RXCORRUPT:
				System.out.print("COMM_RXCORRUPT");
				break;
		}
	}
}
