package org.sudowars.Controller.Local.BluetoothDeviceList;


/**
 * One list element of the bluetooth device list in the {@link MultiplayerMenu}-Activity.
 */
public class BluetoothDeviceListItem {
	/**
	 * the device name
	 */
	private String name;
	
	/**
	 * the mac address
	 */
	private String mac;
	
	/**
	 * the state, if the local and the other bluetooth device are paired
	 */
	private boolean paired;
	
	/**
	 * Constructor which initializes a new instance of this class with the given variables
	 *
	 * @param name the device name
	 * @param mac the mac address
	 * @param paired the state, if the local and the other bluetooth device are paired
	 */
	public BluetoothDeviceListItem (String name, String mac, boolean paired) {
		if (mac == null) {
			throw new IllegalArgumentException("Given mac is null.");
		}
		
		if (name == null) {
			this.name = "";
		} else {
			this.name = name;
		}
		
		this.mac = mac;
		this.paired = paired;
	}

	/**
	 * Returns the device {@link name}.
	 * 
	 * @return the device {@link name}
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the {@link mac} addresse of the bluetooth device.
	 * 
	 * @return the {@link mac} addresse of the bluetooth device
	 */
	public String getMac() {
		return this.mac;
	}

	/**
	 * Returns <code>true</code>, if the local and the other bluetooth device are paired
	 * Otherwise returns <code>false</code>.
	 * 
	 * @return <code>true</code>, if the local and the other bluetooth device are paired
	 */
	public boolean isPaired() {
		return this.paired; 	
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object object) {
		if (object != null && object instanceof BluetoothDeviceListItem
				&& this.name.equals(((BluetoothDeviceListItem) object).getName())
				&& this.mac.equals(((BluetoothDeviceListItem) object).getMac())
				&& this.paired == ((BluetoothDeviceListItem) object).isPaired()) {
			return true;
		} else {
			return false;
		}
	}
}
