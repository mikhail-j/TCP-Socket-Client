/**
* Connect to a TCP based server and reconnect to a reassigned port on the same server
* Qijia (Michael) Jin
* @version 1.0
*
Copyright (C) 2015  Qijia (Michael) Jin

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/

import java.util.*;
import java.io.*;
import java.net.*;

public class MainDataClient {
	private static int DEFAULT_PORT = 2000;						//server is listening for requests on this port

	public static void main(String[] args) {
		Socket SOCKET_HANDLE;
		String ASSIGNED_PORT;
		try {
			SOCKET_HANDLE = new Socket (InetAddress.getLocalHost(), DEFAULT_PORT);					//query for open port
			try {
				BufferedReader buffered_in = new BufferedReader (new InputStreamReader(SOCKET_HANDLE.getInputStream()));
				BufferedWriter buffered_out = new BufferedWriter (new OutputStreamWriter(SOCKET_HANDLE.getOutputStream()));
				
				ASSIGNED_PORT = new String(buffered_in.readLine());
				System.out.println("New Port: " + ASSIGNED_PORT);
				buffered_out.write("Client connecting to new port: " + ASSIGNED_PORT + "\n");
				buffered_out.flush();
				buffered_in.close();
				buffered_out.close();
				SOCKET_HANDLE = null;
				buffered_in = null;
				buffered_out = null;
					try {
						SOCKET_HANDLE = new Socket(InetAddress.getLocalHost(), Integer.parseInt(ASSIGNED_PORT));
						DataInputStream in = new DataInputStream(SOCKET_HANDLE.getInputStream());
						DataOutputStream out = new DataOutputStream(SOCKET_HANDLE.getOutputStream());
						while (true) {
							//do something
						}
					}
					catch (IOException ie) {
						System.out.println("I/O Exception: " + ie.getMessage());
						return;
					}
			}
			catch (IOException ie) {
				System.out.println("I/O Exception: " + ie.getMessage());
				return;
			}
		}
		catch (UnknownHostException uhe) {
						System.out.println("Unknown Host Exception: " + uhe.getMessage());
						return;
		}
		catch (IOException ie) {
				System.out.println("I/O Exception: " + ie.getMessage());
		}
		catch (SecurityException se) {
				System.out.println("Security Exception: " + se.getMessage());
		}
		catch (IllegalArgumentException iae) {
				System.out.println("Illegal Argument Exception: " + iae.getMessage());
		}
	}
}