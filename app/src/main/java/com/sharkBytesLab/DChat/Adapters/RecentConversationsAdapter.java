package com.sharkBytesLab.DChat.Adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sharkBytesLab.DChat.Models.ChatMessage;
import com.sharkBytesLab.DChat.databinding.RecentUserItemBinding;

import java.util.List;

public class RecentConversationsAdapter extends RecyclerView.Adapter<RecentConversationsAdapter.ConversationViewHolder>
{

    private final List<ChatMessage> chatMessages;

    public RecentConversationsAdapter(List<ChatMessage> chatMessages)
    {
        this.chatMessages = chatMessages;
    }

    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new ConversationViewHolder(RecentUserItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position)
    {
        holder.setData(chatMessages.get(position));
    }

    @Override
    public int getItemCount()
    {
        return chatMessages.size();
    }

    class ConversationViewHolder extends RecyclerView.ViewHolder
    {
        RecentUserItemBinding binding;

        ConversationViewHolder(RecentUserItemBinding recentUserItemBinding)
        {
            super(recentUserItemBinding.getRoot());
            binding = recentUserItemBinding;
        }

        void setData(ChatMessage chatMessage)
        {
            binding.recentImageProfile.setImageBitmap(getConversationImage(chatMessage.conversationImage));
            binding.recentTextName.setText(chatMessage.conversationName);
            binding.recentTextMessage.setText(chatMessage.message);
        }


    }

    private Bitmap getConversationImage(String encodedImage)
    {
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
