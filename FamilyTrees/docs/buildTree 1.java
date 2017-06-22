	/**
	 *
	 * @param <E>
	 * @param size
	 * 				the number of nodes in the subtree to be built
	 * @param prestart
	 * 				the place in pretrav where the preorder traversal of this subtree begins
	 * @param poststart
	 * 				the place in posttrav where the post order traversal of this subtree begins
	 */
	public static TreeNode<Character> buildTree(int size, int prestart, int poststart) {
		if (size == 1) {
			return new TreeNode<Character>(pretrav[prestart]);
		}
		if (pretrav[prestart] == posttrav[size - (prestart + 1)]) {
			TreeNode<Character> root = new TreeNode<Character>(pretrav[prestart]);
			ArrayList<Character> family = new ArrayList<Character>();
			List<Character> pretravList = Arrays.asList(pretrav);
			List<Character> posttravList = Arrays.asList(posttrav);

			int numChildren = 0;
			int pretravIdx = prestart + 1;
			int posttravIdxCurr = 0;
			int posttravIdxPrev = -1;
			//family.add(pretrav[prestart]);
			//family.add(pretrav[pretravIdx]);

			ListIterator<Character> pretravIterator = pretravList.listIterator(pretravIdx);
			ListIterator<Character> posttravIterator = posttravList.listIterator();
			Character c = pretravIterator.next();
			family.add(c);
			int familySize = 1;
			while (posttravIterator.next() != c) {
				familySize++;
			}

			/*
			while (pretravIterator.hasNext()) {
				ListIterator<Character> posttravIterator = posttravList.listIterator();
				while (pretravIdx < (size + prestart)) {
					posttravIdxCurr = indexOf(pretrav[pretravIdx], posttrav);
					pretravIdx += (posttravIdxCurr - posttravIdxPrev);
					family.add(pretrav[pretravIdx]);
					posttravIdxPrev = posttravIdxCurr;
					//numChildren++;
				}
			}
			*/

			for (int k = 0; k < family.size(); k++) {
				familySize = pretrav[family.get(k)] - posttrav[family.get(k - 1)];
				TreeNode<Character> n = buildTree(familySize, pretrav[family.get(k)], posttrav[family.get(k - 1)]);
				root.addChild(n);
				n.setParent(root);
			}
			return root;
		}

		return null;
	}

		private static int indexOf(Character c, Character[] array) {
			for (int k = 0; k < array.length; k++) {
				if (array[k].equals(c)) {
					return k;
				}
			}
			return -1;
		}

		public static int numChildren(Character[] pretrav, Character[] posttrav) {
			int size = pretrav.length;
			int prestart = 0;
			int numChildren = 0;
			int pretravIdx = prestart + 1;
			int posttravIdxCurr = 0;
			int posttravIdxPrev = -1;
			while (pretravIdx < (size + prestart)) {
				posttravIdxCurr = indexOf(pretrav[pretravIdx], posttrav);
				pretravIdx += (posttravIdxCurr - posttravIdxPrev);
				posttravIdxPrev = posttravIdxCurr;
				numChildren++;
			}
			return numChildren;
		}

		public static int familySize(Character rootC) {
			int famSize = 0;
			CircularLinkedList<Character> pretravList = toCircularList(pretrav);
			CircularLinkedList<Character> posttravList = toCircularList(posttrav);
			ListIterator<Character> preCursor = pretravList.listIterator(0);
			pretravList.find(rootC);
			Character subRootC = pretravList.get(pretravList.find(rootC) + 1);
			ListIterator<Character> postCursor = posttravList.listIterator(posttravList.find(rootC));
			Character postChar = postCursor.next();

			System.out.println("rootC = " + rootC);
			do  {
				System.out.println("postChar = " + postChar);
				System.out.println("subRootC = " + subRootC);
				while (postChar != subRootC) {
					System.out.println("preCursor.next() = " + preCursor.next());
					//preCursor.next();
					postChar = postCursor.next();
					System.out.println("postChar = " + postChar);
					System.out.println("-----inner----");
				}
				subRootC = preCursor.next();
				famSize++;
				System.out.println("=====outer========");
			} while (postChar != rootC);
			return famSize;
		}

		public static CircularLinkedList<Character> toCircularList(Character[] array) {
			CircularLinkedList<Character> list = new CircularLinkedList<Character>();
			for (int k = 0; k < array.length; k++) {
				list.append(array[k]);
			}
			return list;
	}