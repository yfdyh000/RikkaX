package moe.shizuku.support.helplib;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import moe.shizuku.support.utils.IOUtils;
import ru.noties.markwon.Markwon;
import moe.shizuku.helplib.R;

public class MarkdownFragment extends Fragment {

    public static MarkdownFragment newInstance(@RawRes int res) {
        Bundle args = new Bundle();
        args.putInt(Intent.EXTRA_TEXT, res);

        MarkdownFragment fragment = new MarkdownFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.helplib_content_markdown, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final int res = getArguments().getInt(Intent.EXTRA_TEXT, 0);
        if (res == 0) {
            return;
        }

        final TextView textView = view.findViewById(android.R.id.text1);
        textView.post(new Runnable() {
            @Override
            public void run() {
                Markwon.setMarkdown(textView, IOUtils.toString(getResources().openRawResource(res)));
            }
        });
    }
}
