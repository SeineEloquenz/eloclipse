package nz.eloque.eloclipse;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PlatformUI;

public final class JavaTabLabelModifier implements IPartListener2 {
	@Override
	public void partOpened(IWorkbenchPartReference partRef) {
		if (partRef.getPart(false) instanceof final IEditorPart editor
				&& editor.getEditorInput() instanceof IURIEditorInput) {
			// When opened.
			fixTitle(partRef, editor);

			// When saved.
			editor.addPropertyListener((source, propId) -> {
				if (propId == IEditorPart.PROP_DIRTY && !editor.isDirty()) {
					fixTitle(partRef, editor);
				}
			});
		}
	}

	static void fixTitle(IWorkbenchPartReference partRef, IEditorPart editor) {
		final var title = editor.getEditorInput().getName();
		final var uniqueTitle = title + "___eloclipse___";
		setTitle(partRef, uniqueTitle);
		findTabFolders(partRef, uniqueTitle);
		setTitle(partRef, title.replace(".java", ""));
	}

	static void setTitle(IWorkbenchPartReference partRef, String title) {
		try {
			final var model = partRef.getClass().getMethod("getModel").invoke(partRef, (Object[]) null);
			model.getClass().getMethod("setLabel", String.class).invoke(model, title);
		} catch (final Exception ex) {
			new Exception("Unable to set tab title (Eloclipse).", ex).printStackTrace();
		}
	}

	static void findTabFolders(IWorkbenchPartReference partRef, String title) {
		final var part = partRef.getPart(false);
		if (part != null && part.getSite() != null) {
			final var parent = part.getSite().getShell();
			if (parent != null) {
				findTabFolders(parent, title);
			}
		}
	}

	static private boolean findTabFolders(Composite parent, String title) {
		for (final var child : parent.getChildren()) {
			if (child instanceof final CTabFolder tabFolder) {
				if (hideClose(tabFolder, title)) {
					return true;
				}
			} else if (child instanceof final Composite composite) {
				if (findTabFolders(composite, title)) {
					return true;
				}
			}
		}
		return false;
	}

	static private boolean hideClose(CTabFolder tabFolder, String title) {
		for (final var item : tabFolder.getItems()) {
			if (item.getText().equals(title)) {
				item.setShowClose(false);

				try {
					final var field = CTabFolder.class.getDeclaredField("showClose");
					field.setAccessible(true);
					field.set(tabFolder, false);
				} catch (final Exception ex) {
					new Exception("Unable to set CTabFolder#showClose false (Eloclipse).", ex).printStackTrace();
				}

				tabFolder.setMinimumCharacters(30);
				return true;
			}
		}
		return false;
	}

	@Override
	public void partActivated(IWorkbenchPartReference partRef) {
	}

	@Override
	public void partDeactivated(IWorkbenchPartReference partRef) {
	}

	@Override
	public void partBroughtToTop(IWorkbenchPartReference partRef) {
	}

	@Override
	public void partClosed(IWorkbenchPartReference partRef) {
	}

	@Override
	public void partHidden(IWorkbenchPartReference partRef) {
	}

	@Override
	public void partVisible(IWorkbenchPartReference partRef) {
	}

	@Override
	public void partInputChanged(IWorkbenchPartReference partRef) {
	}

	static public void register() {
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService()
				.addPartListener(new JavaTabLabelModifier());
	}
}