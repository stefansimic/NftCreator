package entities;

public class NftAssetComponent {
	private NftAssetComponentEnum name;
	private String fileName;
	private boolean useDefaultFile;

	public NftAssetComponent(NftAssetComponentEnum name, boolean useDefaultFile) {
		this.name = name;
		this.useDefaultFile = useDefaultFile;

		this.fileName = name + "00001";
	}

	public NftAssetComponentEnum getComponentName() {
		return this.name;
	}

	// TODO check if filename sets ending .pnn
	public String getComponentFileId() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		if (!useDefaultFile) {
			this.fileName = fileName;
		}
	}
}
