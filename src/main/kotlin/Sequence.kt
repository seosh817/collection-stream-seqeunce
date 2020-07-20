import java.util.*
import kotlin.Comparator

object Sequence {


    /* Sequence는 Stream과 마찬가지로 지연 개발 (lazy) 컬렉션 연산을 하므로
    중간 결과 값을 저장하지 않으므로 연산의 속도를 빠르게 할 수 있다. */
    //그러나 최종연산을 사용하지 않는다면 중간연산은 실행되지 않으므로 최종연산을 사용해야 할 경우만 사용해야 함

    // 리스트, 해쉬맵, 큐, 스택

    @JvmStatic
    fun main(args: Array<String>) {



        //sort(), find(), filter(), map(),  groupBy(), max()


        // 리스트

        // 오름차순으로 정렬하기
        val numberList = listOf("d", "c", "b", "a")
        numberList.asSequence()
            .sortedWith(Comparator { t1, t2 ->
                if (t1 > t2) {
                    1
                } else {
                    -1
                }
            })
            .forEach(System.out::println)


        //리스트 선언
        val nums = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).asSequence()

        val two = nums.find {
            it == 2
        }.let(System.out::println)


        //짝수만 걸러내기 (2, 4, 6, 8, 10)
        val evens = nums
            .filter {
                it % 2 == 0
            }

        // 홀수만 걸러내기 (1, 3, 5, 7, 9)
        val odds = nums
            .filter {
                it % 2 == 1
            }


        /*
        데이터를 모으는(accumulate) 함수는 reduce()와 fold()가 있다.
        둘의 차이점은 reduce는 초기값 없이 배열의 첫번째부터 시작하고
        fold()는 내가 지정한 초기값부터 시작한다.
        적용한 리스트가 비어있는 리스트라면 exception이 발생한다.
        그래서 비어있는 리스트일 가능성이 있다면 가급적 fold() 사용하자
        (Java의 Stream에서는 둘 다 reduce()로 사용하고 매개변수가 하나 더 있음)

         */

        //reduce()
        nums.reduce { total, num ->
            total + num
        }.let{
            println("reduce() = $it")
        }

        //fold()
        nums.fold(10) { total, num ->
            total + num
        }.let{
            println("fold() = $it")
        }

        nums.forEachIndexed { index, value ->
            println("index = $index, value = $value")
        }


        //zip은 두개의 Sequence(List)의 원소들을 순서대로 합친다.
        //만약 두 Sequence(List)의 원소 갯수가 다르다면
        //적은 숫자의 List의 갯수만 합쳐짐
        odds.zip(evens) { a, b -> "zip() -> ${Pair(a,b)}" }
            .toList().let(System.out::println)

        //chunk는 사이즈만큼 잘라서 리스트 만듬
        val chunkList = nums.chunked(3).forEachIndexed { index, list ->
            println("$index -> $list")
        }

        //joinToString -> 문자열을 합침
        val joinToString1 = nums.joinToString().let {
            println("joinToString1 = $it")
        }

        val joinToString2 = nums.joinToString(separator = "->", prefix = "카운트 시작 ", postfix = " 카운트 끝").let {
            println("joinToString2 = $it")
        }

        //any(), none()
        //any()는 컬렉션 내에 데이터가 존재하면 true 반환
        //none()은 그 반대
        val none = nums.any{ number ->
            number == 3
        }.let(System.out::println)

        //distinct() -> 중복 값 제거한 리스트 반환

        // 값들을 2배로 만든 리스트
        // sequence는 중간연산은 항상 지연 계산이기 때문에 최종연산이 호출되지 않으면 filter()와 map()이 불리지 않음
        // 그래서 toList() (최종연산) 를 붙여야 늦춰졌던 계산들이 수행된다.
        val doubleList = nums
            .filter {
                println("filter($it)");
                it%2 == 0 }
            .map {
                it * it
                println("map($it)");
            }.toList()


        //sequnece 적용 안한 것
        // 모든 원소를 access 함
        println(listOf(1,2,3,4).map { print("access $it ");it*it }.find { print("result $it "); it>3 })

        //sequence 적용
        // 2번째 원소의 find()메소드에서 결과가 나온 후 바로 뒤의 연산을 실행하지 않음
        println(listOf(1,2,3,4)
            .asSequence()
            .map { print("access $it ");it*it }
            .find { print("result $it "); it>3 })


        // list의 값들을 key로 한 Map 생성
        val map = nums.groupBy {
            it
        }.forEach(System.out::println)


        //리스트 중 최대값 찾기 1번째
        val max = nums.max().let(System.out::println)

        //리스트 중 최대값 찾기 2번째
        val max2 = nums.maxBy {
            it
        }.let(System.out::println)

        val max3 = nums.maxWith(Comparator { p0, p1 ->
                when {
                p0 > p1 -> 1  // 내꺼가 더 크면 참
                p0 == p1 -> 0
                else -> -1
            }
        }).let(System.out::println)





        // 해쉬맵 선언
        val hashMap = hashMapOf<Int, String>(Pair(3, "c"), Pair(4, "d"), Pair(2, "b"), Pair(1, "a"))

        // key를 기준으로 정렬된 맵
        val sortedByKeyMap: Map<Int, String> = hashMap.toList().sortedBy { it.first }.toMap()

        sortedByKeyMap.forEach { (i, s) ->
            println("key = $i , value = $s")
        }

        // key를 기준으로 정렬된 리스트
        val sortedByKeyList: Array<Int> = hashMap.toList().sortedBy { it.first }.toMap().keys.toTypedArray()
/*        val sortedByKeyList: IntArray = hashMap.toList().sortedBy { it.first }.toMap().keys.toIntArray()*/

        // value를 기준으로 정렬된 리스트
        val sortedByValueList: Array<String> = hashMap.toList().sortedBy { it.second }.toMap().values.toTypedArray()

        val filteredPair = sortedByKeyMap.filter {
            it.key == 2
        }.let(System.out::println)


        
        //해쉬맵 순회 두가지 방법
        hashMap.forEach { entry ->
            println("key = ${entry.key} value = ${entry.value}")
        }
        
        for(entry in hashMap.entries) {
            println("key = ${entry.key} value = ${entry.value}")
        }




        val queue: Queue<Int> = LinkedList(listOf(1,2,3,4))

        queue.peek().let(System.out::println) // 큐의 맨 앞 원소 단순 참조 (안꺼냄)

/*        queue.poll().let(System.out::println)*/ //큐의 맨 앞 원소 반환 (꺼냄)

        queue.offer(5) // 큐에 원소 집어넣기

        queue.remove() // 맨 앞 원소 제거


        queue.asSequence().map {
            println("$it")
            it * 2
        }.toList()

        println(queue)


        val stack = Stack<Int>()

        stack.push(1)
        stack.push(2)
        stack.push(3)
        stack.push(4)
        stack.push(5)

        // (1, 2, 3, 4, 5)

        stack.peek().let(System.out::println) // 스택의 맨 위에 원소 단순 참조 (안꺼냄)
        stack.pop().let(System.out::println) // 스택의 맨 위에 원소 반환 (꺼냄)

        // (1, 2, 3, 4)

        stack.search(3).let(System.out::println) // 스택의 원소 3의 인덱스 반환 (2 반환)

        stack.elementAt(3).let(System.out::println) // 3의 인덱스에 있는 원소 반환 (4 반환)

        stack.first().let(System.out::println) // 첫번쨰 원소 반환 (1 반환)

        stack.indices.let(System.out::println) // Range 반환 (0..3 반환)
        


    }
}