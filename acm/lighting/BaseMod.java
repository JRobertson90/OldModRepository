//   Greg's Mod Base - Generic Mod

package acm.lighting;

public class BaseMod {

	interface IBlock {
		public void setRenderType(int id);
		public String getQualifiedRendererClassName();
	}
	
	interface ITileEntity {
		public void onAddedToWorld();
	}
	
}