   M S B A M L   `   `   `  ����D  @windowapp, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null%clr-namespace:windowapp	windowapp  5   	       windowapp.Window1   5   	   R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   )localclr-namespace:windowapp     5   	     � Title$  Window1��5   	   $	��608��6   $	��991��6&   .��5	      � -   5         �Name$ second_grid��5	      .+�5
   
   �� $
��Name��6   $
��Left=�6   $��108,121,0,0q�6:   $	��Top=�6O    J� RenderTransformOrigin$ 
0.541,0.42&�6}   $	��134��6�   $��18(�6�   ���	#FF424040$
��Bold"�6�   $��Italic$�6�   ��6g   Z� 6�   �� 5   
   -   5         ��Name$
 Name��5      $
��Left=�6    $��
75,160,0,0q�6;    �� TextWrapping$
 Wrap=�6O   $	��Top=�6c   $	��232��6{   $��32��6�   $��18(�6�   $
��Bold"�6�   ���	#FF424040 �� SelectionBrush �	#FFEBF3F96�   �� 5   
   $��Year of study��6   $
��Left=�6(   $��105,211,0,0q�6C   $	��Top=�6X   $ 
0.541,0.42&�6�   $	��133��6�   $��18(�6�   ���	#FF424040$
��Bold"�6�   $��Italic$�6�   ��6p   Z� 6�   �� 5   
   -   5        $ 
study_year��5      $
��Left=�6&   $��
75,250,0,0q�6A   $
 Wrap=�6U   $	��Top=�6i   $	��232��6�   $��32��6�   $��18(�6�   $
��Bold"�6�   ���	#FF424040 6�   Z� �� 5   
   $	��Age��6   $
��Left=�6   $��105,300,0,0q�69   $	��Top=�6N   $ 
0.541,0.42&�6|   $	��133��6�   $��18(�6�   ���	#FF424040$
��Bold"�6�   $��Italic$�6�   ��6f   Z� 6�   �� 5   
   -   5        $	 Age��5      $
��Left=�6   $��
75,340,0,0q�6:   $
 Wrap=�6N   $	��Top=�6b   $	��232��6z   $��32��6�   $��18(�6�   $
��Bold"�6�   ���	#FF424040 6�   Z� �� 5   
   $��Label��6   $
��Left=�6    $	��592��6;   $��Center=�6H   $��65��6c   ���	#FF4240406n   �� 5   
   $H��B                                                            Search��6   $
��Left=�6]   $��58��6x   $��65,0,0,0q�6�   $	��Top=�6�   $	��926��6�   ���	#FF424040���	#FFFBF2F2$��24(�6�   �� 5   
   $�� ��6   $
��Left=�6   $	��534��66   $��
323,58,0,0q�6C   $	��Top=�6W   $��8��6o   ���	#FF4240406y   �� 5   
   -   5         ��Name$ add_btn��5      $	��Add��6"   $
��Left=�60   $��31��6K   $��
75,418,0,0q�6W   $	��Top=�6k   $��67��6�   ���	#FF0A8DF1$��16(�6�   ���	#FFF5F1F16�   �� 5   
   -   5        $ 	delet_btn��5      $��Delete��6$   $
��Left=�65   $��31��6P   $��229,418,0,0q�6\   $	��Top=�6q   $��67��6�   ���	#FFF54242���	#FFFBF6F6$��16(�6�   6�   �� 5   
   -   5        $ search��5      $��Search��6!   $
��Left=�62   $��31��6M   $��152,473,0,0q�6Y   $	��Top=�6n   $��67��6�   ���	#FF1EEF64$
��Bold"�6�   ���	#FF272323$��16(�6�   6�   &   System.Windows.Controls.DataGrid  5   
   -   5          Name$ 	Data_grid��5      $��
331,60,0,2q�6&    � 	Resources 5      =  7System.Windows.Controls.Primitives.DataGridColumnHeader	 �� 
TargetType
 �� Property �� Value��%�   '        �� 5      "	  6   .�5      �� 
 � ��
 ���@�65   �� 5      
 � ��$ SemiBold"�65   �� 5      
 � ��$ 0,0,1,2q�6:   �� 5      
 � ��
 �   �66   5      5         Columns 5       0  *System.Windows.Controls.DataGridTextColumn  5!      ,  &System.Windows.Controls.DataGridColumn   Header$       Student Name��6%      Width5  /System.Windows.Controls.DataGridLengthConverter$	 220 6A   1  +System.Windows.Controls.DataGridBoundColumn   Binding 6M   �� *student_name+  5"      $ Student Year of study��6%   $	 220 6D    6P   �� *student_year_study+  5#      $   Student Age��6%   $ * 6<    6F   �� *student_age+5%      5&      5'      