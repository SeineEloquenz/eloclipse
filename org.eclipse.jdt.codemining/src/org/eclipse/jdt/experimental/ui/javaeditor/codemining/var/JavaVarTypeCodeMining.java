package org.eclipse.jdt.experimental.ui.javaeditor.codemining.var;

import java.util.concurrent.CompletableFuture;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.codemining.ICodeMiningProvider;
import org.eclipse.jface.text.codemining.LineContentCodeMining;

public class JavaVarTypeCodeMining extends LineContentCodeMining {

	private final VariableDeclarationFragment fragment;

	public JavaVarTypeCodeMining(VariableDeclarationFragment fragment, ITextViewer viewer, ICodeMiningProvider provider) {
		super(new Position(fragment.getName().getStartPosition() + fragment.getName().getLength(), 1), provider, null);
		this.fragment = fragment;
	}

	@Override
	protected CompletableFuture<Void> doResolve(ITextViewer viewer, IProgressMonitor monitor) {
		return CompletableFuture.runAsync(() -> {
			ITypeBinding typeBinding = fragment.resolveBinding().getType();
			super.setLabel(": " + toLabel(typeBinding));
		});
	}

	private String toLabel(ITypeBinding binding) {
        	String fullType = binding.getName();
        	return fullType.replaceAll("<[^>]*+>", "<...>");
    	}
}
