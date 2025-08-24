package nz.eloque.eloclipse;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class Activator extends AbstractUIPlugin implements IStartup {
	@Override
	public void earlyStartup() {
		PlatformUI.getWorkbench().getDisplay().asyncExec(() -> {
			final var modifier = new JavaTabLabelModifier();

			// Existing editors.
			final var window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			if (window != null) {
				final var page = window.getActivePage();
				if (page != null) {
					for (final var partRef : page.getEditorReferences()) {
						modifier.partOpened(partRef);
					}
				}
			}

			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().addPartListener(modifier);
		});
	}
}